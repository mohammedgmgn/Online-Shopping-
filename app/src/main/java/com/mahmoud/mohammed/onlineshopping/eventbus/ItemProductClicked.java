package com.mahmoud.mohammed.onlineshopping.eventbus;


import com.mahmoud.mohammed.onlineshopping.models.Product;

/**
 * Created by mohammed on 17/11/2017.
 */

public class ItemProductClicked {
   private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public ItemProductClicked(Product product) {
        this.product= product;
    }
    public Product getProduct() {
        return product;
    }

}
