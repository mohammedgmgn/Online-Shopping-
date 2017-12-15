package com.mahmoud.mohammed.onlineshopping.ui.detail.component;

import com.mahmoud.mohammed.onlineshopping.ui.detail.DetailActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.detail.module.DetailModule;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.DetailActivity;

import dagger.Component;

/**
 * Created by mohammed on 10/12/2017.
 */
@DetailActivityScope
@Component(modules = {DetailModule.class})
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
