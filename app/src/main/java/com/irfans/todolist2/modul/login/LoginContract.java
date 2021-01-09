package com.irfans.todolist2.modul.login;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToProfile();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
