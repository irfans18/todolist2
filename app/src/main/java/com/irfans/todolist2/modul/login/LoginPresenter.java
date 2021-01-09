package com.irfans.todolist2.modul.login;

import com.irfans.todolist2.data.model.User;
import com.irfans.todolist2.data.source.session.SessionRepository;

/**
 * Created by fahrul on 13/03/19.
 */

public class LoginPresenter implements com.irfans.todolist2.modul.login.LoginContract.Presenter{
    private final com.irfans.todolist2.modul.login.LoginContract.View view;
    private final SessionRepository sessionRepository;                                              //new

    public LoginPresenter(com.irfans.todolist2.modul.login.LoginContract.View view, SessionRepository sessionRepository) {           //add
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){                                             //new
            view.redirectToProfile();                                                               //jika sudah login langsung masuk profile
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        //proses login

        //if login success
        User loggedUser = new User(email, "TOKEN123456");                                    //new
        sessionRepository.setSessionData(loggedUser);                                               //new

        //then call redirect to profile
        view.redirectToProfile();
    }

}
