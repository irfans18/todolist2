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
    public void saveData(String title, String description) {
        view.requestEditTask(title, description, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }

    @Override
    public void loadData() {
        view.requestTaskDetail(new RequestCallback<EditTaskResponse>() {
            @Override
            public void requestSuccess(EditTaskResponse data) {
                view.setResult(data.getTask());
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }
}
