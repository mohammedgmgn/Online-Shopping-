package com.mahmoud.mohammed.onlineshopping.authnication.login.presenter;


import com.mahmoud.mohammed.onlineshopping.base.BasePresenter;

/**
 * Created by Mohamed on 11/20/2017.
 */

public interface LoginPresenter extends BasePresenter {

    boolean validateLoginFields();
    void login();
}
