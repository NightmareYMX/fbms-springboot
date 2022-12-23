package com.ymx.fbms.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**")
//                .addResourceLocations(String.format("file:%s\\src\\main\\resources\\static\\profile\\", System.getProperty("user.dir")));
                .addResourceLocations("file:D:/Java/project/com.ymx.fbms/src/main/resources/static/profile/");
    }
}
