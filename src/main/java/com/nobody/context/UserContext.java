package com.nobody.context;

import com.nobody.enums.AuthenticationTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description 用户上下文
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class UserContext {
    // 用户名称
    private String name;
    // 用户ID
    private String userId;
    // 用户类型
    private AuthenticationTypeEnum type;
}
