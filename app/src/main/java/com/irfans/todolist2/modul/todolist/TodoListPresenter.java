package com.irfans.todolist2.modul.todolist;

import android.util.Log;

import java.util.List;

import com.irfans.todolist2.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

import static android.content.ContentValues.TAG;

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
    public void getData() {
            view.requestPrivateTasks(new RequestCallback<List<Task>>() {
                @Override
                public void requestSuccess(List<Task> data) {
                    Log.e(TAG, data.get(0).getTitle());
                    view.setResult(data);
                }

                @Override
                public void requestFailed(String errorMessage) {
                    view.showFailedMessage(errorMessage);
                }
            });
    }
}
