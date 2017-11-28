package com.mahmoud.mohammed.onlineshopping.authnication.login.presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;
import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.interfaces.LoginView;
/**
 * Created by mohammed on 27/11/2017.
 */

public class LoginPresnterImpl implements LoginPresenter, OnSuccessListener<AuthResult>, OnFailureListener {
    private LoginView view;
    private LoginActivty mContext;

    public LoginPresnterImpl(LoginActivty context) {
    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(AuthResult authResult) {

    }

    @Override
    public void setView(BaseView view) {

    }

    @Override
    public boolean validateLoginFields() {
        return false;
    }

    @Override
    public void login() {

    }
}
