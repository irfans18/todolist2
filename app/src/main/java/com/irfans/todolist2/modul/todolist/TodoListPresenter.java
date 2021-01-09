package com.irfans.todolist2.modul.todolist;

import java.util.ArrayList;

import com.irfans.todolist2.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public class TodoListPresenter implements TodoListContract.Presenter{
    private final TodoListContract.View view;



    public TodoListPresenter(TodoListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getDataSet() {
        view.getDataFromDatabase();
        //get Data from DB
//        ArrayList<Task> data = new ArrayList<Task> ();
//        data.add(new Task("1","Task 1", "Kerjakan task satu", "Besok"));
//        data.add(new Task("2", "Task 2", "Kerjakan task dua","Besok"));
    }

}
