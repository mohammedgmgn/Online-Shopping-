package com.mahmoud.mohammed.onlineshopping.ui.categories.products.presnter;

import com.mahmoud.mohammed.onlineshopping.base.BasePresenter;

/**
 * Created by mohammed on 29/11/2017.
 */

public interface ProductPresnter extends BasePresenter {
    void getBooksProductList();

    void getHomeProductList();

    void getLifeStyleProductList();

    void getElectronicsProductList();

    void getOffersProductList();

    void setProductList();

    void getMyCartList();
    void getOrders();
}
