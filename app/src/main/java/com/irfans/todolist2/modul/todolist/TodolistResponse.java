package com.irfans.todolist2.modul.todolist;

import com.irfans.todolist2.model.Task;

import java.util.List;

public class TodolistResponse {
    public List<Task> task;

    public TodolistResponse(List<Task> task) {
        this.task = task;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
