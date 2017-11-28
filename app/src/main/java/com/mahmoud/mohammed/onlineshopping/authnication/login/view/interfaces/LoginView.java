package com.mahmoud.mohammed.onlineshopping.authnication.login.view.interfaces;


import com.mahmoud.mohammed.onlineshopping.base.BaseView;

/**
 * Created by Mohammed on 24/11/17.
 */

public interface LoginView extends BaseView {
    void showProgress();

    void hideProgress();

    void setEmailError(String errorMessage);

    void setPasswordError(String errorMessage);

    void onLoginFail(String errorMessage);

    void navigateToHome();

    String getEmail();
    String getPassword();

}
