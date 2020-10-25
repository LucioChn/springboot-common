package com.nobody.exception;

import lombok.Getter;

/**
 * @Description 接口异常信息定义
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Getter
public enum RestAPIError {

    /*
     * 基本异常
     */
    SYSTEM_ERROR("10001", "系统错误"),

    USER_NOT_EXISTS("10002", "用户不存在"),

    AUTH_ERROR("10003", "没有权限"),

    PARAMETER_UNSUITABLE("10004", "参数不当");

    private String errorCode;
    private String errorMsg;

    RestAPIError(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
