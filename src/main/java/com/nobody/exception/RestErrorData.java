package com.nobody.exception;

import org.slf4j.MDC;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description 异常数据封装
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class RestErrorData {

    private String errorCode;
    private String errorMsg;
    private String traceId = MDC.get("traceId");
    private String errorUri;

    public RestErrorData(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RestErrorData(RestAPIError restAPIError) {
        super();
        this.errorCode = restAPIError.getErrorCode();
        this.errorMsg = restAPIError.getErrorMsg();
    }
}
