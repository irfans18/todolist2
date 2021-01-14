package com.irfans.todolist2.modul.todolist;

import java.util.List;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;
import com.irfans.todolist2.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

/**
 * Created by fahrul on 13/03/19.
 */

public interface TodoListContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask();
        void redirectToEdit(Task task);
        void setResult(List<Task> data);
        void requestPrivateTasks(RequestCallback<List<Task>> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void getData();
    }
}
