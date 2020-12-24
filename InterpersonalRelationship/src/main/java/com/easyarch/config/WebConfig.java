package com.easyarch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
   // 这里之所以多了一"/",是为了解决打war时访问不到问题
//        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");

//        registry.addResourceHandler("/login/**").addResourceLocations("classpath:/login/");

    }
}
