package com.irfans.todolist2.modul.task;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;

public interface TaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToProfile();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
