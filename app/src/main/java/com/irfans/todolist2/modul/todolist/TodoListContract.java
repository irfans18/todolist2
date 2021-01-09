package com.irfans.todolist2.modul.todolist;

import java.util.ArrayList;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;
import com.irfans.todolist2.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public interface TodoListContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask();
        void getDataFromDatabase();
    }

    interface Presenter extends BasePresenter {
//        ArrayList<Task> getDataSet();
        void getDataSet();
    }
}
