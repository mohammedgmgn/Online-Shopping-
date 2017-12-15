package com.mahmoud.mohammed.onlineshopping.ui.categories.products.modules;

import com.mahmoud.mohammed.onlineshopping.ui.categories.products.presnter.ProductPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.home.HomeActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammed on 30/11/2017.
 */
@Module
public class ProductsModule {
    @Provides
    @HomeActivityScope
    ProductPresnterImpl provideProductPresnterIml(HomeActivity context) {
        return new ProductPresnterImpl(context);
    }

}
