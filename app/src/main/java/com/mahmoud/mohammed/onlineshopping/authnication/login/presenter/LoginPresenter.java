package com.mahmoud.mohammed.onlineshopping.authnication.login.presenter;


import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;
import com.mahmoud.mohammed.onlineshopping.base.BasePresenter;

/**
 * Created by Mohamed on 11/20/2017.
 */

public interface LoginPresenter {

    boolean validateLoginFields();
    void login();
    void setView(LoginActivty view);

}
