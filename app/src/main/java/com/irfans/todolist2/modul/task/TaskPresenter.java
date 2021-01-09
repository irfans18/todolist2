package com.irfans.todolist2.modul.task;

public class TaskPresenter implements TaskContract.Presenter {
    private final TaskContract.View view;

    public TaskPresenter(TaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(final String email, final String password) {
        //proses login
        //if login success call redirect to profile
        view.redirectToProfile();
    }
}
