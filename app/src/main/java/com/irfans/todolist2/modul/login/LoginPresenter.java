package com.irfans.todolist2.modul.login;

import com.irfans.todolist2.utils.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginActivity activity;
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view, LoginActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
    }

    @Override
    public void performLogin(String email, String password){
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.saveToken(data.token);
                view.saveUser(data.user);
                view.redirectToHome();
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}