package com.irfans.todolist2.modul.newtask;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;
import com.irfans.todolist2.model.SuccessMessage;
import com.irfans.todolist2.utils.RequestCallback;

/**
 * Created by fahrul on 13/03/19.
 */

public interface NewTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showSuccessMessage(SuccessMessage data);
        void requestNewTask(String title, String description, RequestCallback<SuccessMessage> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description);
    }
}
