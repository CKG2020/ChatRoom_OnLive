package com.utils;

import lombok.Data;

/**
 * @author tycoding
 * @date 2019-06-13
 */
@Data
public class R {

    private int code = 200;

    private String msg = "success";

    private Object data;

    public R() {
        super();
    }

    public R(Object data) {
        super();
        this.data = data;
    }

    public R(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
