package com.nobody.config;

import com.nobody.context.UserContextResolver;
import com.nobody.interceptor.UserPermissionInterceptor;
import com.nobody.interceptor.UserPermissionInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    private UserPermissionInterceptor userPermissionInterceptor;

    private UserPermissionInterceptorAdapter userPermissionInterceptorAdapter;

    private UserContextResolver userContextResolver;

    @Autowired
    public void setUserPermissionInterceptor(UserPermissionInterceptor userPermissionInterceptor) {
        this.userPermissionInterceptor = userPermissionInterceptor;
    }

    @Autowired
    public void setUserPermissionInterceptorAdapter(
            UserPermissionInterceptorAdapter userPermissionInterceptorAdapter) {
        this.userPermissionInterceptorAdapter = userPermissionInterceptorAdapter;
    }

    @Autowired
    public void setUserContextResolver(UserContextResolver userContextResolver) {
        this.userContextResolver = userContextResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可以添加多个拦截器，一般只添加一个
        // addPathPatterns("/**") 表示对所有请求都拦截
        // .excludePathPatterns("/base/index") 表示排除对/base/index请求的拦截
        // 多个拦截器可以设置order顺序，值越小，preHandle越先执行，postHandle和afterCompletion越后执行
        // order默认的值是0，如果只添加一个拦截器，可以不显示设置order的值
        registry.addInterceptor(userPermissionInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/base/index").order(0);
        // registry.addInterceptor(userPermissionInterceptorAdapter).addPathPatterns("/**")
        // .excludePathPatterns("/base/index").order(1);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userContextResolver);
    }
}
