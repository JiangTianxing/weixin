package org.redrock.weixin.config;

import org.redrock.weixin.interceptor.InitInterceptor;
import org.redrock.weixin.resolver.SnsapiBaseResolver;
import org.redrock.weixin.resolver.SnsapiUserInfoResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.redrock.weixin.controller"
})

public class ServletConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InitInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SnsapiBaseResolver());
        argumentResolvers.add(new SnsapiUserInfoResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}

