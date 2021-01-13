package com.irfans.todolist2.modul.edittask;

import android.widget.Toast;

import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter{
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

    @Override
    public void finish(Task task) {
        view.finishTask(task.getId(), new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                Toast.makeText(activity, data.getMessage(), Toast.LENGTH_SHORT).show();
                view.showSuccessMessage(data);
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }

    @Override
    public void delete(Task task) {
        view.deleteTask(task.getId(), new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                Toast.makeText(activity, data.getMessage(), Toast.LENGTH_SHORT).show();
                view.showSuccessMessage(data);
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }
}
