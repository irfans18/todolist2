package com.irfans.todolist2.modul.profile;

public class ProfilePresenter implements ProfileContract.Presenter {
    private final ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogout() {
        view.redirectToLogin();
    }

    @Override
    public void initializeProfile(String email, String password) {
        String emailText = "Email : " + email;
        String passwordText = "Password : " + password;

        view.setTvEmailText(emailText);
        view.setTvPasswordText(passwordText);
    }
}
