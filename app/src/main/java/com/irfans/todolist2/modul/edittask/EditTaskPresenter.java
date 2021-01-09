package com.irfans.todolist2.modul.edittask;

import com.irfans.todolist2.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;

    public EditTaskPresenter(EditTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(final String title, final String description, String date){
//        Task newTask = new Task("3", title, description);
        //save new task
        //then go back to task list
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        //load data task by id
        //then send data to fragment
//        Task task = new Task("3", "title of taskIndex:"+id, "description of taskIndex:"+id);
//        view.showData(task);
    }

}
