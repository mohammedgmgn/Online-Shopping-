package com.mahmoud.mohammed.onlineshopping.base;

/**
 * Created by siko on 9/3/2017.
 */

public interface BasePresenter<V extends BaseView> {
    void setView(V view);
}
