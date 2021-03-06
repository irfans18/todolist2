package com.irfans.todolist2.utils;

public interface RequestCallback<T> {
    void requestSuccess(T data);
    void requestFailed(String errorMessage);
}

