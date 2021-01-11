package com.irfans.todolist2.modul.edittask;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;
import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.utils.RequestCallback;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void setResult(Task data);
        void requestEditTask(String title, String description, RequestCallback<SuccessMessage> requestCallback);
        void requestTaskDetail(RequestCallback<EditTaskResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description);
        void loadData();
    }
}
