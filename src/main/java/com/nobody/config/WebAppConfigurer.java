package com.nobody.config;

import com.nobody.interceptor.UserPermissionInterceptor;
import com.nobody.interceptor.UserPermissionInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserPermissionInterceptor userPermissionInterceptor;

    // @Autowired
    // private UserPermissionInterceptorAdapter userPermissionInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可以添加多个拦截器，一般只添加一个
        // addPathPatterns("/**") 表示对所有请求都拦截
        // .excludePathPatterns("/base/index") 表示排除对/base/index请求的拦截
        registry.addInterceptor(userPermissionInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/base/index");
        // registry.addInterceptor(userPermissionInterceptorAdapter).addPathPatterns("/**")
        // .excludePathPatterns("/base/index");
    }
}
