package com.mahmoud.mohammed.onlineshopping.authnication.login.component;

import com.mahmoud.mohammed.onlineshopping.authnication.login.LoginActivityScope;
import com.mahmoud.mohammed.onlineshopping.authnication.login.modules.LoginModule;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;

import dagger.Component;

/**
 * Created by Mohamed on 11/19/2017.
 */
@LoginActivityScope
@Component(modules = {LoginModule.class} )
public interface LoginComponent {

    void injectLoginActivity(LoginActivty activity);
}
