package com.mahmoud.mohammed.onlineshopping.ui.orders.component;

import com.mahmoud.mohammed.onlineshopping.ui.categories.products.modules.ProductsModule;
import com.mahmoud.mohammed.onlineshopping.ui.orders.OrderDetailsScope;
import com.mahmoud.mohammed.onlineshopping.ui.orders.module.OrderDetailsModule;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.fragments.MyOrdersFragment;

import dagger.Component;

/**
 * Created by mohammed on 14/12/2017.
 */
@OrderDetailsScope
@Component(modules = {OrderDetailsModule.class,ProductsModule.class})

public interface OrderDetailsComponent {
    void injectOrderDetailActivity(OrderDetailsActivity activity);
}
