package com.touzone.stsb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**") // 웹에서 접근할 URL 패턴
                .addResourceLocations("file:///D:/upload/"); // 실제 폴더 경로
        		//.addResourceLocations("file:////minicookei/upload/"); // 실제 폴더 경로
    }
}
