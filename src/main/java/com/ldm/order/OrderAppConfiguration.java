package com.ldm.order;

import com.ldm.order.interceptor.ValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OrderAppConfiguration implements WebMvcConfigurer {


    @Bean
    public ValidationInterceptor getValidateTokenInterceptor(){
        return new ValidationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getValidateTokenInterceptor()).addPathPatterns("/**");
    }

}
