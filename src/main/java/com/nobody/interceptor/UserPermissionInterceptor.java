package com.nobody.interceptor;

import com.nobody.annotation.UserAuthenticate;
import com.nobody.context.UserContext;
import com.nobody.context.UserContextManager;
import com.nobody.exception.RestAPIError;
import com.nobody.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Slf4j
@Component
public class UserPermissionInterceptor implements HandlerInterceptor {

    private UserContextManager userContextManager;

    @Autowired
    public void setContextManager(UserContextManager userContextManager) {
        this.userContextManager = userContextManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) {

        log.info(">>> UserPermissionInterceptor preHandle -- ");

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 获取用户权限校验注解(优先获取方法，无则再从类获取)
            UserAuthenticate userAuthenticate =
                    handlerMethod.getMethod().getAnnotation(UserAuthenticate.class);
            if (null == userAuthenticate) {
                userAuthenticate = handlerMethod.getMethod().getDeclaringClass()
                        .getAnnotation(UserAuthenticate.class);
            }
            if (userAuthenticate != null && userAuthenticate.permission()) {
                // 获取用户信息
                UserContext userContext = userContextManager.getUserContext(request);
                // 权限校验
                if (userAuthenticate.type() != userContext.getType()) {
                    // 如若不抛出异常，也可返回false
                    throw new RestException(RestAPIError.AUTH_ERROR);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        log.info(">>> UserPermissionInterceptor postHandle -- ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {
        log.info(">>> UserPermissionInterceptor afterCompletion -- ");
    }
}
