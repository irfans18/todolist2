package com.irfans.todolist2.modul.login;

import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        LoginFragment loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }


}