package com.app.neos.config;

import com.app.neos.interceptor.AlarmInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final AlarmInterceptor alarmInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**").addResourceLocations("file:///C:/neos/upload/");


    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alarmInterceptor).addPathPatterns("/main/**","/community/**","/error","/inquiry/**","/my-page/**","/pay/**","/neosUser/**"
                ,"/notice/**","/search/**","/store/**","/study/**"
        );
    }
}
