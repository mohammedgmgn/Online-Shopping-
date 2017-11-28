package com.mahmoud.mohammed.onlineshopping.authnication.register.component;

import com.mahmoud.mohammed.onlineshopping.authnication.register.SignUpActivityScope;
import com.mahmoud.mohammed.onlineshopping.authnication.register.modules.SignUpModule;
import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;

import dagger.Component;

/**
 * Created by mohammed on 28/11/2017.
 */
@SignUpActivityScope
@Component(modules = {SignUpModule.class} )
public interface SignUpComponent {
    void injectSignUpActivity(SignUpActivity activity);

}
