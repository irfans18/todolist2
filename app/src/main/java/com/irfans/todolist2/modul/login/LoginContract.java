package com.irfans.todolist2.modul.login;

import com.irfans.todolist2.base.BasePresenter;
import com.irfans.todolist2.base.BaseView;
import com.irfans.todolist2.data.model.User;
import com.irfans.todolist2.utils.RequestCallback;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void redirectToHome();
        void saveToken(String token);
        void saveUser(User user);
        void requestLogin(String email, String password, final RequestCallback<LoginResponse> requestCallback);
        void showFailedMessage(String message);
        void showSuccessMessage();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
