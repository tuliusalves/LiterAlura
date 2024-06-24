package com.tulius.literAlura.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("ttp://127.0.0.1:5500")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","TRACE","CONNECT");
    }
}
