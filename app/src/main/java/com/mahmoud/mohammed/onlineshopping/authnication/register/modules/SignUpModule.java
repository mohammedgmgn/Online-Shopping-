package com.mahmoud.mohammed.onlineshopping.authnication.register.modules;
import com.mahmoud.mohammed.onlineshopping.authnication.register.SignUpActivityScope;
import com.mahmoud.mohammed.onlineshopping.authnication.register.presnter.SignUpPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammed on 28/11/2017.
 */
@Module
public class SignUpModule {
    SignUpActivity signUpActivity;
    public SignUpModule(SignUpActivity signUpActivity){
        this.signUpActivity = signUpActivity;
    }

    @Provides
    @SignUpActivityScope
    SignUpPresnterImpl provideSignUpPresnter(SignUpActivity context){
        return new SignUpPresnterImpl(context);
    }



    @Provides
    @SignUpActivityScope
    SignUpActivity signUpActivity(){
        return signUpActivity;
    }


}
