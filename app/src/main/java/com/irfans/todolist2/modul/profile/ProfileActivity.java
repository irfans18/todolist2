package com.irfans.todolist2.modul.profile;

import android.content.Intent;
import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;
import com.irfans.todolist2.utils.SharedPreferencesUtil;

public class ProfileActivity extends BaseFragmentHolderActivity {
    ProfileFragment profileFragment;
    SharedPreferencesUtil sharedPreferencesUtil;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        ProfileContract.Presenter profilePresenter;
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        profileFragment = new ProfileFragment(email, password, sharedPreferencesUtil);
        setCurrentFragment(profileFragment, false);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtBackOnClick();
            }
        });
//        profilePresenter = profileFragment.getPresenter();
//        profilePresenter.initializeProfile(name, email);
    }

    public void setBtBackOnClick() {
        ProfileContract.Presenter profilePresenter = profileFragment.getPresenter();

        profilePresenter.performLogout();
    }
}
