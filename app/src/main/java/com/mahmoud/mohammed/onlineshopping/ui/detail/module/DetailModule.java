package com.mahmoud.mohammed.onlineshopping.ui.detail.module;

import com.mahmoud.mohammed.onlineshopping.ui.detail.DetailActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.detail.presnter.ProductDetailPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.DetailActivity;
import com.mahmoud.mohammed.onlineshopping.ui.home.HomeActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammed on 10/12/2017.
 */
@Module
public class DetailModule {
    DetailActivity detailActivity;
    public DetailModule(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    @Provides
    @DetailActivityScope
    DetailActivity detailActivity() {
        return detailActivity;
    }
    @Provides
    @DetailActivityScope
    ProductDetailPresnterImpl provideProductDetailPresnterIml(DetailActivity context) {
        return new ProductDetailPresnterImpl(context);
    }



}
