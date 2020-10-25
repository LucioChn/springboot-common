package com.nobody.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 自定义异常
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Getter
@Setter
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 5564446583860234738L;

    private RestErrorData restErrorData;

    public RestException(RestAPIError restAPIError, String errorMsg) {
        super(errorMsg);
        restErrorData = new RestErrorData(restAPIError.getErrorCode(), errorMsg);
    }

    public RestException(RestAPIError restAPIError) {
        super(restAPIError.getErrorMsg());
        restErrorData = new RestErrorData(restAPIError.getErrorCode(), restAPIError.getErrorMsg());
    }

    public RestException(String errorCode, String errorMsg) {
        super(errorMsg);
        restErrorData = new RestErrorData(errorCode, errorMsg);
    }
}
