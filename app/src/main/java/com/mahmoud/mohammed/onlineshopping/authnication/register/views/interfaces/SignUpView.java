package com.mahmoud.mohammed.onlineshopping.authnication.register.views.interfaces;

import com.mahmoud.mohammed.onlineshopping.models.User;

/**
 * Created by mohammed on 28/11/2017.
 */

public interface SignUpView {
    void showProgress();

    void hideProgress();

    void setEmailError(String errorMessage);

    void setPasswordError(String errorMessage);

    void onLoginFail(String errorMessage);

    void navigateToHome();
void showMessage(String message);
    String getEmail();

    String getPassword();
    String getConfirmedPassword();
    String getBirthDate();
    String getName();



}
