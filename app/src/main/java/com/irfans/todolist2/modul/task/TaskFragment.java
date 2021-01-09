package com.irfans.todolist2.modul.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.modul.login.LoginActivity;
import com.irfans.todolist2.modul.profile.ProfileActivity;
import com.irfans.todolist2.utils.SharedPreferencesUtil;

public class TaskFragment extends BaseFragment<LoginActivity, TaskContract.Presenter> implements
        TaskContract.View {
    EditText etEmail;
    EditText etPassword;
    Button btLogin;
    SharedPreferencesUtil sharedPreferencesUtil;

    public TaskFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new TaskPresenter(this);
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btLogin = fragmentView.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtLoginClick();
            }
        });

        setTitle("My Login View");

        return fragmentView;
    }

    public void setBtLoginClick() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mPresenter.performLogin(email, password);
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        activity.finish();
    }
}