package com.irfans.todolist2.modul.edittask;

import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

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
    public void saveData(Task task) {
        view.requestEditTask(task, new RequestCallback<SuccessMessage>() {
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

    @Override
    public void loadData(Task task) {
        view.setResult(task);
    }
}
