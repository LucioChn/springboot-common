package com.nobody.exception;

import javax.servlet.http.HttpServletRequest;

import com.nobody.pojo.model.GeneralResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 接口全局统一异常处理
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = RestException.class)
    @ResponseBody
    public GeneralResult<RestErrorData> restErrorHandler(HttpServletRequest request,
            RestException e) {
        RestErrorData r = e.getRestErrorData();
        r.setErrorUri(request.getRequestURI());
        log.error(r.toString(), e);
        return GeneralResult.genErrorResult(r.getErrorMsg(), r.getErrorCode(), r.getTraceId());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GeneralResult<RestErrorData> errorHandler(HttpServletRequest request, Exception e) {
        RestErrorData r = new RestErrorData(RestAPIError.SYSTEM_ERROR);
        r.setErrorUri(request.getRequestURI());
        log.error(r.toString(), e);
        return GeneralResult.genErrorResult(e.getMessage(), r.getErrorCode(), r.getTraceId());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public GeneralResult<RestErrorData> errorHandler(HttpServletRequest request,
            MethodArgumentNotValidException e) {
        RestErrorData r = new RestErrorData(RestAPIError.PARAMETER_UNSUITABLE);
        StringBuilder message = new StringBuilder();
        e.getBindingResult().getAllErrors()
                .forEach(error -> message.append(error.getDefaultMessage()).append(";"));
        String des = message.toString();
        if (!StringUtils.isEmpty(des)) {
            r.setErrorMsg(des.substring(0, des.length() - 1));
        }
        r.setErrorUri(request.getRequestURI());
        log.error(r.toString(), e);
        return GeneralResult.genErrorResult(r.getErrorMsg(), r.getErrorCode(), r.getTraceId());
    }
}
