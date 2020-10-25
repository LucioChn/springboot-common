package com.nobody.annotation;

import com.nobody.enums.AuthenticationTypeEnum;

import java.lang.annotation.*;

/**
 * @Description 校验访问权限注解
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface UserAuthenticate {
    /**
     * 是否需要校验访问权限 默认不校验
     * 
     * @return
     */
    boolean permission() default false;

    /**
     * 验证类型，默认游客
     * 
     * @return
     */
    AuthenticationTypeEnum type() default AuthenticationTypeEnum.VISITOR;
}
