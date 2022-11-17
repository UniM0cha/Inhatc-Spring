package kr.inhatc.spring.solstice_shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value(value = "${uploadPath}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 브라우저에서 /images/** 로 접근하면 uploadPath와 연결해주겠다는 의미이다.
        registry.addResourceHandler("/images/**").addResourceLocations(uploadPath);
    }

}
