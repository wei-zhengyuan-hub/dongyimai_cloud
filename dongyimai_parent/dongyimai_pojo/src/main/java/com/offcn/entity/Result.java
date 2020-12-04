package com.offcn.entity;

import java.io.Serializable;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.entity
 * @date 2020/11/3 20:16
 * @Copyright © 2020-2021 中公教育
 */
public class Result implements Serializable {

    private Boolean success; //是否成功
    private String message; //提示语

    public Result() {
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
