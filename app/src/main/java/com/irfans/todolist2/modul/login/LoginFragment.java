package com.irfans.todolist2.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.data.source.session.UserSessionRepository;
import com.irfans.todolist2.databinding.FragmentLoginBinding;
import com.irfans.todolist2.modul.todolist.TodoListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by fahrul on 13/03/19.
 */

public class LoginFragment extends BaseFragment<com.irfans.todolist2.modul.login.LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    FragmentLoginBinding binding;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new LoginPresenter(this, new UserSessionRepository(getActivity()));                      //add
        mPresenter.start();

//        etEmail = fragmentView.findViewById(R.id.et_email);
//        etPassword = fragmentView.findViewById(R.id.et_password);
//        btnLogin = fragmentView.findViewById(R.id.bt_login);
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        setTitle("My Login View");

        return fragmentView;
    }

    public void setBtLoginClick(){
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        mPresenter.performLogin(email,password);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToProfile() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }


}
