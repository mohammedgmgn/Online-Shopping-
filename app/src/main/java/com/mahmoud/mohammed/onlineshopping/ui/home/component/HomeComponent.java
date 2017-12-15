package com.mahmoud.mohammed.onlineshopping.ui.home.component;

import com.mahmoud.mohammed.onlineshopping.ui.orders.view.fragments.MyOrdersFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.books.BooksFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.elctoronics.ElectoronicsFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.homeapplication.HomeApplicationsFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.lifestyle.LifeStyleFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.modules.ProductsModule;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.offers.OffersFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.view.ShoppingCartFragment;
import com.mahmoud.mohammed.onlineshopping.ui.home.HomeActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.home.modules.HomeModule;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import dagger.Component;

/**
 * Created by mohammed on 29/11/2017.
 */

@HomeActivityScope
@Component(modules = {HomeModule.class,ProductsModule.class})
public interface HomeComponent {
    void injectHomeActivity(HomeActivity activity);
    void inject(ElectoronicsFragment electoronicsFragment);
    void inject(LifeStyleFragment lifeStyleFragment);
    void inject(MyOrdersFragment ordersFragment);
    void inject(HomeApplicationsFragment homeApplicationsFragment);
    void inject(BooksFragment booksFragment);
    void inject(OffersFragment offersFragment);
    void inject(ShoppingCartFragment shoppingCartFragment);




}
