package com.mahmoud.mohammed.onlineshopping.ui.orders.view.interfaces;

import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.models.Product;

import java.util.List;

/**
 * Created by mohammed on 14/12/2017.
 */

public interface OrderDetailsViews extends BaseView {
    void displayTotalPrice(double totalPrice);
    void displayOrderdList(List<Product>orderdList);
    void showListEmptyError();
    void showOrderSuccessMessage();

}
