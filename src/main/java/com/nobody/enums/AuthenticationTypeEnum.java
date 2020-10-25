package com.nobody.enums;

import lombok.Getter;

/**
 * @Description 权限验证类型枚举
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Getter
public enum AuthenticationTypeEnum {

    VISITOR(0, "访客"),

    ADMIN(1, "管理员");

    private int index;
    private String value;

    AuthenticationTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static AuthenticationTypeEnum index(int index) {
        for (AuthenticationTypeEnum statusEnum : AuthenticationTypeEnum.values()) {
            if (statusEnum.getIndex() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
