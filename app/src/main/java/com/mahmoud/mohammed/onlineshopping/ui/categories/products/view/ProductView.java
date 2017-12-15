package com.mahmoud.mohammed.onlineshopping.ui.categories.products.view;

import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.models.Product;

import java.util.List;

/**
 * Created by mohammed on 29/11/2017.
 */

public interface ProductView extends BaseView {
    void displayProductList(List<Product> products);

    void displayMyCartList(List<Product> products);

    void displayOrdersList(List<Order> orderList);
}
