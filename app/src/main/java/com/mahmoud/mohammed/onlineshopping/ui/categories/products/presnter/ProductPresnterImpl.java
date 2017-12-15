package com.mahmoud.mohammed.onlineshopping.ui.categories.products.presnter;

import android.content.Context;
import android.webkit.ValueCallback;

import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.communication.FireConstants;
import com.mahmoud.mohammed.onlineshopping.communication.FirebaseReader;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.view.ProductView;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mohammed on 29/11/2017.
 */

public class ProductPresnterImpl implements ProductPresnter {

    ProductView view;
    Context context;

    @Inject
    public ProductPresnterImpl(HomeActivity context) {
        this.context = context;
    }

    @Override
    public void setView(BaseView view) {
        this.view = (ProductView) view;
    }


    @Override
    public void getBooksProductList() {
        getProducts(FireConstants.BOOKS_CHLD);
    }

    @Override
    public void getHomeProductList() {
        getProducts(FireConstants.HOME_APPLICATION_CHILD);
    }

    @Override
    public void getLifeStyleProductList() {
        getProducts(FireConstants.LIFESTYLE_CHLD);
    }

    @Override
    public void getElectronicsProductList() {
        getProducts(FireConstants.ELECTRONICS_CHILD);
    }

    @Override
    public void getOffersProductList() {
        getProducts(FireConstants.OFFERS_CHILD);

    }

    private void getProducts(String ProductType) {

        FirebaseReader.getFireDataList(Product.class, new ValueCallback<ArrayList<Object>>() {
            @Override
            public void onReceiveValue(ArrayList<Object> objects) {
                List<Product> products = (List<Product>) (List<?>) objects;

                view.displayProductList(products);
            }
        }, FireConstants.PRODUCTS_NODE, ProductType);

    }

    /*
    *  for(int i=14;i<getElectronicsUrls().length;i++){
                product=new Product();
                product.setId(i);
                product.setProductName("Item"+i+"Name");
            }

            for (int i=1;i<=getOffersUrls().length;i++){
                product.setDescription("Item Description");
                product.setPrice("99");
                product.setQuantity(5);
                product.setImageUrl(getOffersUrls()[i-1]);

                FirebaseWriter.writeWithAutoKey(product, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                },FireConstants.PRODUCTS_NODE,FireConstants.ELECTRONICS_CHILD);
            }

    * */
    @Override
    public void setProductList() {
      /*  for (int i=1;i<=getOffersUrls().length;i++){
            Product product=new Product();
            product.setProductName("Item"+i+"Name");
            product.setDescription("Item Description");
            product.setPrice("99");
            product.setQuantity(5);
            product.setImageUrl(getOffersUrls()[i-1]);
            product.setId(i);

            FirebaseWriter.writeWithAutoKey(product, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                }
            },FireConstants.PRODUCTS_NODE,FireConstants.OFFERS_CHILD);
        }*/

      /*  int k = 64;

        for (int i = 1; i <= getBooksUrls().length; i++) {
           Product product = new Product();
            product.setId(k);
            product.setProductName("Item" + i + "Name");

            product.setDescription("Item Description");
            product.setPrice("99");
            product.setQuantity(5);
            product.setImageUrl(getBooksUrls()[i - 1]);
                k++;
            FirebaseWriter.writeWithAutoKey(product, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                }
            }, FireConstants.PRODUCTS_NODE, FireConstants.BOOKS_CHLD);
        }*/

    }

    @Override
    public void getMyCartList() {
        FirebaseReader.getFireDataList(Product.class, new ValueCallback<ArrayList<Object>>() {
            @Override
            public void onReceiveValue(ArrayList<Object> objects) {
                List<Product> products = (List<Product>) (List<?>) objects;

                view.displayMyCartList(products);
            }
        }, FireConstants.MY_CARTS, SessionHelper.getUser().getUid());

    }

    @Override
    public void getOrders() {
        FirebaseReader.getFireDataList(Order.class, new ValueCallback<ArrayList<Object>>() {
            @Override
            public void onReceiveValue(ArrayList<Object> objects) {
                List<Order> orders = (List<Order>) (List<?>) objects;
                view.displayOrdersList(orders);

            }
        }, FireConstants.ORDER_NODE, SessionHelper.getUser().getUid());
    }


}
