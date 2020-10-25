package com.nobody.pojo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 接口通用返回对象
 * @Author Mr.nobody
 * @Date 2020/10/25
 * @Version 1.0
 */
@Getter
@Setter
public class GeneralResult<T> {

    private boolean success = true;

    private String errorCode;

    private String message = "";

    private T data;

    private String traceId;

    public static <T> GeneralResult<T> newInstance() {
        return new GeneralResult<T>();
    }

    /**
     * normal
     *
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> genResult(boolean success, T data, String message) {
        GeneralResult<T> result = GeneralResult.newInstance();
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> genSuccessResult(T data) {

        return genResult(true, data, null);
    }

    /**
     * error message
     *
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> genErrorResult(String message) {

        return genResult(false, null, message);
    }

    // /**
    // * error
    // *
    // * @param error error enum
    // * @return
    // */
    // public static GeneralResult<RestErrorData> genErrorResult(RestErrorData error) {
    //
    // return genResult(false, error, error.getError());
    // }

    /**
     * success no message
     *
     * @return
     */
    public static <T> GeneralResult<T> genSuccessResult() {
        return genSuccessResult(null);
    }

    public static <T> GeneralResult<T> genErrorResult(String message, String errorCode) {

        return genResult(false, null, message, errorCode);
    }

    public static <T> GeneralResult<T> genResult(boolean success, T data, String message,
            String errorCode) {
        GeneralResult<T> result = GeneralResult.newInstance();
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        result.setErrorCode(errorCode);
        return result;
    }

    public static <T> GeneralResult<T> genErrorResult(String message, String errorCode,
            String traceId) {
        GeneralResult<T> result = GeneralResult.newInstance();
        result.setSuccess(false);
        result.setMessage(message);
        result.setErrorCode(errorCode);
        result.setTraceId(traceId);
        return result;
    }

}
