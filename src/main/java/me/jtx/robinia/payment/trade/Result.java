package me.jtx.robinia.payment.trade;

import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;

public class Result<T> {
    // 错误/正确码
    private Integer code;
    // 提示信息
    private String msg;
    // 返回的数据
    private T data;

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code) {
        this.code = code;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Result() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> createBySuccessResultMessage(String msg) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> createBySuccessCodeResult(Integer code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> createBySuccessResult(String msg, T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> createBySuccessResult() {
        return new Result<T>(ResultEnum.SUCCESS.getCode());
    }

    public static <T> Result<T> createByErrorResult() {
        return new Result<T>(ResultEnum.ERROR.getCode());
    }

    public static <T> Result<T> createByErrorResult(String msg, T data) {
        return new Result<T>(ResultEnum.ERROR.getCode(), msg, data);
    }

    public static <T> Result<T> createByErrorCodeResult(Integer errorCode, String msg) {
        return new Result<T>(errorCode, msg);
    }

    public static <T> Result<T> createByErrorResultMessage(String msg) {
        return new Result<T>(ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> createByErrorResultMessage(List<String> msg) {
        String errorMsg = StringUtils.join(msg);
        return new Result<T>(ResultEnum.ERROR.getCode(), errorMsg);
    }
}
