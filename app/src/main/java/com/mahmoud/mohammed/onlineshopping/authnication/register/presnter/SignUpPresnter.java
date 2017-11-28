package com.mahmoud.mohammed.onlineshopping.authnication.register.presnter;

import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;

/**
 * Created by mohammed on 28/11/2017.
 */

public interface SignUpPresnter {
    void register();
    void setView(SignUpActivity view);
    boolean validateSignUpFields();

}
