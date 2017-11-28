package com.mahmoud.mohammed.onlineshopping.authnication.login.component;

import com.limozein.user.application.component.ApplicationComponent;
import com.limozein.user.ui.login.LoginActivityScope;
import com.limozein.user.ui.login.modules.LoginModule;
import com.limozein.user.ui.login.view.activities.LoginActivity;

import dagger.Component;

/**
 * Created by Mohamed on 11/19/2017.
 */
@LoginActivityScope
@Component(modules = {LoginModule.class} , dependencies = {ApplicationComponent.class})
public interface LoginComponent {

    void injectLoginActivity(LoginActivity activity);
}
