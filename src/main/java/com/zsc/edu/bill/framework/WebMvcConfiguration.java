package com.zsc.edu.bill.framework;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author harry_yao
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final WebProperties webProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations(webProperties.getResources().getStaticLocations())
            .resourceChain(webProperties.getResources().getChain().isCache())
            .addResolver(new FallbackPathResourceResolver());
    }

    private static class FallbackPathResourceResolver extends PathResourceResolver {
        @Override
        public Resource resolveResource(
                HttpServletRequest request,
                @NonNull String requestPath,
                @NonNull List<? extends Resource> locations,
                @NonNull ResourceResolverChain chain) {
            Resource resource = super.resolveResource(request, requestPath, locations, chain);
            if (resource == null) {
                resource = super.resolveResource(request, "/index.html", locations, chain);
            }
            return resource;
        }
    }
}
