package com.irfans.todolist2.modul.newtask;

import com.irfans.todolist2.model.SuccessMessage;
import com.irfans.todolist2.utils.RequestCallback;

/**
 * Created by fahrul on 13/03/19.
 */

public class NewTaskPresenter implements NewTaskContract.Presenter {
    private final NewTaskContract.View view;
    private NewTaskActivity context;

    public NewTaskPresenter(NewTaskContract.View view, NewTaskActivity context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(String title, String description) {
        context.startLoading();
        view.requestNewTask(title, description, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                context.stopLoading();
                view.showSuccessMessage(data);
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                context.stopLoading();
            }
        });
    }
}
