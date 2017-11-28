package com.mahmoud.mohammed.onlineshopping.authnication.login.view.interfaces;


import com.mahmoud.mohammed.onlineshopping.base.BaseView;

/**
 * Created by Mohammed on 24/11/17.
 */

public interface ForgetPasswordView extends BaseView {

    void showProgressDialog();

    void hideProgressDialog();

    void showErrorMessage(String message);

    void showSuccessMessage(String message);

    void finish();

}
