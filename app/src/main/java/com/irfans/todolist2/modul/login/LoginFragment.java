package com.irfans.todolist2.modul.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import com.google.android.material.textfield.TextInputLayout;
import com.irfans.todolist2.R;
import com.irfans.todolist2.SecondActivity;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.data.model.User;
import com.irfans.todolist2.modul.SecondFragment;
import com.irfans.todolist2.modul.todolist.TodoListActivity;
import com.irfans.todolist2.utils.RequestCallback;
import com.irfans.todolist2.utils.SharedPreferences.TokenSharedUtil;
import com.irfans.todolist2.utils.SharedPreferences.UserSessionRepository;
import com.irfans.todolist2.utils.SharedPreferences.UserSharedUtil;
import com.irfans.todolist2.utils.SharedPreferences.UtilProvider;
import com.irfans.todolist2.utils.myURL;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    private EditText etEmail;
    private EditText etPassword;
    private final TokenSharedUtil tokenSharedUtil;
    private final UserSharedUtil userSharedUtil;

    public LoginFragment() {
        this.tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        this.userSharedUtil = UtilProvider.getUserSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setItems(){
        TextInputLayout tilEmail;
        TextInputLayout tilPassword;
        Button btnLogin;
        TextView tvRegister;

        etEmail = fragmentView.findViewById(R.id.login_email_et);
        etPassword = fragmentView.findViewById(R.id.login_password_et);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tvRegister = fragmentView.findViewById(R.id.register);
        tilEmail = fragmentView.findViewById(R.id.login_email_til);
        tilPassword = fragmentView.findViewById(R.id.login_password_til);

        tilEmail.setHintEnabled(false);
        tilPassword.setHintEnabled(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        tvRegister.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvRegisterClick();
                return true;
            }
        });
    }



    public void setBtLoginClick() {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            mPresenter.performLogin(email, password);
    }

    public void setTvRegisterClick() {
        Intent intent = new Intent(activity, TodoListActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, SecondActivity.class);
        startActivity(intent);
    }

    public void requestLogin(final String email, String password, final RequestCallback<LoginResponse> requestCallback) {
        AndroidNetworking.post(myURL.LOGIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                        } else if (response.token == null) {
                            requestCallback.requestFailed("Email atau Password Salah");
                        } else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 400) {
                            requestCallback.requestFailed("Email atau Password Salah");
                        }
                        else if (anError.getErrorCode() == 500){
                            requestCallback.requestFailed("Ada yang Salah");
                        }
                    }
                });
    }

    public void saveToken(String token) {
        tokenSharedUtil.setToken(token);
    }

    public void saveUser(User user){
        userSharedUtil.setUser(user);
    }

    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showSuccessMessage() {
        String name = userSharedUtil.getUser().getEmail();
        Toast.makeText(getContext(), "Selamat Datang, " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}