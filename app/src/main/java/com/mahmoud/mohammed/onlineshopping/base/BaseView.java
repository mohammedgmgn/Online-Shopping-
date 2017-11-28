package com.mahmoud.mohammed.onlineshopping.base;

/**
 * Created by siko on 9/3/2017.
 */

public interface BaseView {
    void showMessage(String message);
    void showLoadingDialog(String message);
    void showErrMsg(String msg);
    void showNoInternetMsg();

}
