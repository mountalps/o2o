package com.imooc.o2o.dto;

/**
 * JSON object
 * all return can use this
 */
public class Result<T> {

    //success = true if succeed
    //success = false if fail
    private boolean success;

    //return T type of data
    private T data;

    //error message
    private String errorMsg;

    //erro code
    private int errorCode;

    public Result() {
    }

    // success constructor
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // failure constructor
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
