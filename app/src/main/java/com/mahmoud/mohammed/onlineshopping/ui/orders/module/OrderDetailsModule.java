package com.mahmoud.mohammed.onlineshopping.ui.orders.module;

import com.mahmoud.mohammed.onlineshopping.ui.home.HomeActivityScope;
import com.mahmoud.mohammed.onlineshopping.ui.home.presnter.HomePresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.OrderDetailsScope;
import com.mahmoud.mohammed.onlineshopping.ui.orders.presnter.OrderDetailsPresenterImp;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammed on 14/12/2017.
 */

@Module
public class OrderDetailsModule {
    OrderDetailsActivity orderDetailsActivity;
    public OrderDetailsModule(OrderDetailsActivity orderDetailsActivity) {
        this.orderDetailsActivity = orderDetailsActivity;
    }

    @Provides
    @OrderDetailsScope
    OrderDetailsActivity orderDetailsActivity() {
        return orderDetailsActivity;
    }
    @Provides
    @OrderDetailsScope
    OrderDetailsPresenterImp provideOrderDetailPresnter(OrderDetailsActivity context){
        return new OrderDetailsPresenterImp(context);
    }

}
