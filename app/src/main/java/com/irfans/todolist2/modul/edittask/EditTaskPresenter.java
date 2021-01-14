package com.irfans.todolist2.modul.edittask;

import com.irfans.todolist2.model.SuccessMessage;
import com.irfans.todolist2.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter {
    private final EditTaskContract.View view;
    private EditTaskActivity activity;

    public EditTaskPresenter(EditTaskContract.View view, EditTaskActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(Task task) {
        activity.startLoading();
        view.requestEditTask(task, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                activity.stopLoading();
                view.showSuccessMessage(data);
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
            }
        });
    }

    @Override
    public void loadData(Task task) {
        view.setResult(task);
    }


    @Override
    public void delete(Task task) {
        activity.startLoading();
        view.deleteTask(task.getId(), new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                activity.stopLoading();
                view.showSuccessMessage(data);
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
            }
        });
    }
}
