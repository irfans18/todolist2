package com.irfans.todolist2.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.modul.login.LoginActivity;
import com.irfans.todolist2.utils.SharedPreferencesUtil;

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter>
        implements ProfileContract.View {
    String email;
    String password;
    ImageView ivProfilePicture;
    TextView tvEmail;
    TextView tvPassword;
    SharedPreferencesUtil sharedPreferencesUtil;


    public ProfileFragment(String email, String password, SharedPreferencesUtil sharedPreferencesUtil) {
        this.email = email;
        this.password = password;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedStateInstance) {
        super.onCreateView(inflater, container, savedStateInstance);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        ivProfilePicture = fragmentView.findViewById(R.id.ivProfilePicture);
        tvEmail = fragmentView.findViewById(R.id.tvEmail);
        tvPassword = fragmentView.findViewById(R.id.tvPassword);

        ivProfilePicture.setImageResource(R.drawable.ic_launcher_foreground);

        mPresenter = new ProfilePresenter(this);
        mPresenter.start();
        mPresenter.initializeProfile(email, password);

        setTitle("My Profile");

        return fragmentView;
    }

    @Override
    public void setTvPasswordText(String text) {
        tvPassword.setText(text);
    }

    @Override
    public void setTvEmailText(String text) {
        tvEmail.setText(text);
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);

        startActivity(intent);
        activity.finish();
    }

    public ProfileContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
