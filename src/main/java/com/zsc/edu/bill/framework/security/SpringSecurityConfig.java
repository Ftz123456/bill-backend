package com.zsc.edu.bill.framework.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import jakarta.annotation.Resource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.sql.DataSource;

/**
 * @author harry_yao
 */
@AllArgsConstructor
@Configuration
public class SpringSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final SessionRegistry sessionRegistry;
    private final SecurityBeanConfig securityBeanConfig;

    @Resource
    private final DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(securityBeanConfig.passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/api/rest/user/login");
        filter.setAuthenticationManager(authenticationManager());
        filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
        return filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/rest/user/me","/api/rest/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/rest/user/login","/api/rest/user/register").permitAll()
                        .requestMatchers("/api/**").authenticated())
                .addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                    .loginPage("/user/login")
                    .loginProcessingUrl("/api/rest/user/login")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler))
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {}))
                // 添加自定义未授权和未登录结果返回
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .rememberMe(rememberMe -> rememberMe
                        .userDetailsService(userDetailsService)
                        .tokenRepository(persistentTokenRepository()))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/internal/**", "/api/rest/user/logout","/api/rest/user/register"))
                .sessionManagement(session -> session
                        .maximumSessions(3)
                        .sessionRegistry(sessionRegistry)
                        .expiredSessionStrategy(new CustomSessionInformationExpiredStrategy()))
                .build();

    }
}
