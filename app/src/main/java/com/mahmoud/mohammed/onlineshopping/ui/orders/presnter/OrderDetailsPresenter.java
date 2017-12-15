package com.mahmoud.mohammed.onlineshopping.ui.orders.presnter;

import android.content.Intent;

import com.mahmoud.mohammed.onlineshopping.base.BasePresenter;
import com.mahmoud.mohammed.onlineshopping.models.Product;

import java.util.List;

/**
 * Created by mohammed on 14/12/2017.
 */

public interface OrderDetailsPresenter extends BasePresenter {
    void calculateTotalPrice();
    void displayOrderdList();
    void removeOrderedItem(int position);
    void setPresenterData(Intent intent);
    void editOrderedItem(Product product);
    void confirmOrderPressed(String address);

}
