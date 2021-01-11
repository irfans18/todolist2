package com.irfans.todolist2.modul.todolist;

import java.util.ArrayList;
import java.util.List;

import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

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
    public void getData(int privacy) {
        if (privacy == 0){
            view.requestPrivateTasks(new RequestCallback<List<Task>>() {
                @Override
                public void requestSuccess(List<Task> data) {
                    view.setResult(data);
                }

                @Override
                public void requestFailed(String errorMessage) {
                    view.showFailedMessage(errorMessage);
                }
            });
        }else {
            view.requestPublicTasks(new RequestCallback<List<Task>>() {
                @Override
                public void requestSuccess(List<Task> data) {
                    view.setResult(data);
                }

                @Override
                public void requestFailed(String errorMessage) {
                    view.showFailedMessage(errorMessage);
                }
            });
        }
    }
}
