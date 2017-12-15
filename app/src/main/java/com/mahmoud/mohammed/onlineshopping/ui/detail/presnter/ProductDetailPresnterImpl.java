package com.mahmoud.mohammed.onlineshopping.ui.detail.presnter;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.OnSuccessListener;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.communication.FireConstants;
import com.mahmoud.mohammed.onlineshopping.communication.FirebaseWriter;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.DetailActivity;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.ProductDetailView;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mohammed on 10/12/2017.
 */

public class ProductDetailPresnterImpl implements ProductDetailPresnter {
    ProductDetailView detailView;
    Context context;
    Product product;

    @Override
    public void setView(BaseView view) {
        detailView = (ProductDetailView) view;
    }

    @Inject
    public ProductDetailPresnterImpl(DetailActivity context) {
        this.context = context;
    }

    @Override
    public void loadProdcutDetails() {
        detailView.displayProductDetail(product);
    }

    @Override
    public void addToMyCart() {

        FirebaseWriter.writeWithCustomKey(product, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                detailView.productAddedToCart();

            }
        }, FireConstants.MY_CARTS, SessionHelper.getUser().getUid(), String.valueOf(product.getId()));


    }

    @Override
    public void launchOrderDetailsScreen() {
        ArrayList <Product>products=new ArrayList<>();
        products.add(product);
detailView.buyNowClicked(products);
    }

    @Override
    public void setPresenterData(Intent intent) {
        product = intent.getExtras().getParcelable(context.getString(R.string.product_key));
    }

}
