package com.irfans.todolist2.model;

import com.irfans.todolist2.base.BaseModel;

public class SuccessMessage extends BaseModel {
    private  boolean success;
    private String message;

    public SuccessMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
