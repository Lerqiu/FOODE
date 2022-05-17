package com.example.foode.config.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CorsInterceptor corsInterceptor;

    public WebConfig(CorsInterceptor corsInterceptor){
        this.corsInterceptor = corsInterceptor;
    }

    //TODO: from what i reed we can do it with spring boot security (?) - its worth to check
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor)
                .addPathPatterns("/api/**");
    }
}
