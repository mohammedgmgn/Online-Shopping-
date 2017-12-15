package com.mahmoud.mohammed.onlineshopping.ui.home.modules;

import com.mahmoud.mohammed.onlineshopping.ui.home.HomeActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.home.presnter.HomePresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammed on 29/11/2017.
 */
@Module
public class HomeModule {
    HomeActivity homeActivity;
    public HomeModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @HomeActivityScope
    HomeActivity homeActivity() {
        return homeActivity;
    }
    @Provides
    @HomeActivityScope
    HomePresnterImpl provideHomePresnter(HomeActivity context){
        return new HomePresnterImpl(context);
    }
}

