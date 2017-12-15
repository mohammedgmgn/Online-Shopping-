package com.mahmoud.mohammed.onlineshopping.authnication.register.presnter;

import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;
import com.mahmoud.mohammed.onlineshopping.base.BasePresenter;

/**
 * Created by mohammed on 28/11/2017.
 */

public interface SignUpPresnter extends BasePresenter {
    void register();
    boolean validateSignUpFields();

}
