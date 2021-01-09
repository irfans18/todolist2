package com.irfans.todolist2.modul.newtask;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface NewTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description, String date);
    }
}
