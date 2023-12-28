package com.zsc.edu.bill.framework.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import jakarta.annotation.Resource;
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

    @Resource
    private final DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

//    @Bean
//    public JsonAuthenticationFilter jsonAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
//        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
//        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
//        filter.setFilterProcessesUrl("/api/rest/user/login");
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 允许用户json登录
        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();
        jsonAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        jsonAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        jsonAuthenticationFilter.setFilterProcessesUrl("/api/rest/user/login");
        jsonAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/rest/user/me").permitAll()
                        .requestMatchers("/api/**").authenticated())
                .addFilterAt(jsonAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/internal/**", "/api/rest/user/logout"))
                .sessionManagement(session -> session
                        .maximumSessions(3)
                        .sessionRegistry(sessionRegistry)
                        .expiredSessionStrategy(new CustomSessionInformationExpiredStrategy()))
                .build();

    }
}
