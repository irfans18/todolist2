package com.irfans.todolist2.modul.newtask;

import com.irfans.todolist2.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public class NewTaskPresenter implements NewTaskContract.Presenter{
    private final NewTaskContract.View view;

    public NewTaskPresenter(NewTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(final String title, final String description, String date){
        Task newTask = new Task("3", title, description, date);
        //save new task
        //then go back to task list
        view.redirectToTaskList();
    }

}
