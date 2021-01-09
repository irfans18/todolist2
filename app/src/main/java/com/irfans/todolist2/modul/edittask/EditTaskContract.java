package com.irfans.todolist2.modul.edittask;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;
import com.irfans.todolist2.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showData(Task task);
        void setId(String id);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description, String date);
        void loadData(String id);
    }
}
