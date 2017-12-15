package com.mahmoud.mohammed.onlineshopping.ui.detail.presnter;

import android.content.Intent;

import com.mahmoud.mohammed.onlineshopping.base.BasePresenter;
import com.mahmoud.mohammed.onlineshopping.models.Product;

/**
 * Created by mohammed on 10/12/2017.
 */

public interface ProductDetailPresnter extends BasePresenter {
     void loadProdcutDetails();
     void addToMyCart();
     void launchOrderDetailsScreen();
     void setPresenterData(Intent intent);
}
