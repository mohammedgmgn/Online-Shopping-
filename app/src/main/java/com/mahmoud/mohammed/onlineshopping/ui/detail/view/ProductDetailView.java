package com.mahmoud.mohammed.onlineshopping.ui.detail.view;

import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammed on 10/12/2017.
 */

public interface ProductDetailView extends BaseView {
    void displayProductDetail(Product product);
    void productAddedToCart();
    void buyNowClicked(ArrayList<Product>products);



}
