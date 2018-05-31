package com.example.testmybatisplus.exception;

import java.util.Map;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 18-5-31
 * @see
 * @since 1.0
 * @desc 自定义业务异常
 */
public class ServiceException extends RuntimeException {

    private int code;
    private String msg;
    private Map<String, Object> extParams;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtParams() {
        return extParams;
    }

    public void setExtParams(Map<String, Object> extParams) {
        this.extParams = extParams;
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "ServiceException{" + "code=" + code + ", msg='" + msg + '\'' + ", extParams=" + extParams + '}';
    }

}