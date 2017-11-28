package com.mahmoud.mohammed.onlineshopping.authnication.login.modules;


import com.mahmoud.mohammed.onlineshopping.authnication.login.LoginActivityScope;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;
import com.mahmoud.mohammed.onlineshopping.authnication.login.presenter.LoginPresnterImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Mohamed on 11/19/2017.
 */

@Module
public class LoginModule {

    LoginActivty loginActivity;
    public LoginModule(LoginActivty loginActivity){
        this.loginActivity = loginActivity;
    }

    @Provides
    @LoginActivityScope
    LoginPresnterImpl provideLoginPresenter(LoginActivty context){
        return new LoginPresnterImpl(context);
    }



    @Provides
    @LoginActivityScope
    LoginActivty loginActivity(){
        return loginActivity;
    }




}
