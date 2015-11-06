package com.bike.entity;

/**
 * Created by Administrator on 2014/10/1.
 */
public class AjaxEntity
{
    private boolean success = false;

    private Object data;

    private String errors;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
