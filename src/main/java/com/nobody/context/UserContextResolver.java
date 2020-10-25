package com.nobody.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 对有UserContext参数的接口，进行拦截注入用户信息
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Component
@Slf4j
public class UserContextResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserContextManager userContextManager;

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        log.info(">>> resolveArgument -- begin...");
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 从缓存获取用户信息赋值到接口参数中
        return userContextManager.getUserContext(request);
    }

    /**
     * 只对UserContext参数进行拦截赋值
     * 
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.getParameterType().equals(UserContext.class)) {
            return true;
        }
        return false;
    }
}
