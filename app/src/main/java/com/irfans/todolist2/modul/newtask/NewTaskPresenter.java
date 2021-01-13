package com.irfans.todolist2.modul.newtask;

import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

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
    public void saveData(String title, String description) {
        view.requestNewTask(title, description, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                view.showSuccessMessage(data);
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }
}
