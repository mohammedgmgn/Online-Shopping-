package com.mahmoud.mohammed.onlineshopping.authnication.login.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.authnication.login.backend.LoginFirebase;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.interfaces.LoginView;
import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.utils.EmailValidator;

import javax.inject.Inject;

/**
 * Created by mohammed on 27/11/2017.
 */

public class LoginPresnterImpl implements LoginPresenter, OnSuccessListener<AuthResult>, OnFailureListener {
    private LoginView view;
    private Context mContext;

    @Inject
    public LoginPresnterImpl(LoginActivty loginActivity) {
        mContext = loginActivity;
    }

    @Override
    public void setView(BaseView view) {
        this.view = (LoginView) view;
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        view.hideProgress();
        view.onLoginFail(e.getLocalizedMessage());

    }

    @Override
    public void onSuccess(AuthResult authResult) {
        view.hideProgress();
        view.navigateToHome();
        view.onLoginSuccess();
    }

    @Override
    public boolean validateLoginFields() {
        if (view.getEmail().isEmpty()) {
            view.setEmailError(mContext.getString(R.string.email));
            return false;
        } else if (!view.getEmail().isEmpty() && !EmailValidator.isValid(view.getEmail())) {
            view.setEmailError(mContext.getString(R.string.enter_valid_email));
            return false;
        } else if (view.getPassword().isEmpty()) {
            view.setPasswordError(mContext.getString(R.string.enter_your_password));
            return false;
        } else if (view.getPassword().length() < 6) {
            view.setPasswordError(mContext.getString(R.string.minimum_password));
            return false;
        }
        return true;
    }

    @Override
    public void login() {
        this.view.showProgress();
        userLogin(view.getEmail(), view.getPassword());
    }


    private void userLogin(String email, String password) {
        LoginFirebase.login(email, password, this, this);
    }


}
