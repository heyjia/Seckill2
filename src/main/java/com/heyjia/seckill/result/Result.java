package com.heyjia.seckill.result;

public class Result<T> {
    private int code;
    private String msg;
    private T date;
    public static <T> Result<T> success(T date){
        return new Result<T>(date);
    }
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getDate() {
        return date;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public Result(int code, String msg, T date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public Result() {
    }

    public Result(T date) {
        this.date = date;
    }
    public Result(CodeMsg codeMsg) {
        if (null != codeMsg) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
