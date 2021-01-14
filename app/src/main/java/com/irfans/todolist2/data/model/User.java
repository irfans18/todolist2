package com.irfans.todolist2.data.model;

import com.irfans.todolist2.base.BaseModel;

public class User extends BaseModel {
    private String email;
    private int id;

    public User(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
