package com.irfans.todolist2.modul.profile;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;

public interface ProfileContract {
    interface Presenter extends BasePresenter {
        void performLogout();
        void initializeProfile(String email, String password);
    }

    interface View extends BaseView<Presenter> {
        void setTvPasswordText(String text);
        void setTvEmailText(String text);
        void redirectToLogin();
    }
}
