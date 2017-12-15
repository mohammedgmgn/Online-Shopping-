package com.mahmoud.mohammed.onlineshopping.ui.detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.detail.component.DaggerDetailComponent;
import com.mahmoud.mohammed.onlineshopping.ui.detail.component.DetailComponent;
import com.mahmoud.mohammed.onlineshopping.ui.detail.module.DetailModule;
import com.mahmoud.mohammed.onlineshopping.ui.detail.presnter.ProductDetailPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements ProductDetailView {
    DetailComponent detailComponent;
    @BindView(R.id.add_to_cart_acton)
    TextView addCartAction;
    @BindView(R.id.buy_now_action)
    TextView buyNowAction;
    @Inject
    ProductDetailPresnterImpl presnter;
    @BindView(R.id.toolbartest)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbartest)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.product_image_view)
    ImageView productIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        detailComponent = DaggerDetailComponent.builder().detailModule(new DetailModule(this))
                .build();
        detailComponent.inject(this);
        initUi();

    }

    private void initUi() {
        presnter.setView(this);
        presnter.setPresenterData(getIntent());
        presnter.loadProdcutDetails();
        setSupportActionBar(toolbar);
        addCartAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presnter.addToMyCart();
            }
        });

        buyNowAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presnter.launchOrderDetailsScreen();
                //finish();
            }
        });
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showLoadingDialog(String message) {

    }

    @Override
    public void showErrMsg(String msg) {

    }

    @Override
    public void showNoInternetMsg() {

    }

    @Override
    public void displayProductDetail(Product product) {
        Glide.with(this).load(product.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(productIv);
        collapsingToolbar.setTitle(product.getProductName());

    }

    @Override
    public void productAddedToCart() {
        Toast.makeText(this,"Product added to your cart",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void buyNowClicked(ArrayList<Product> products) {
        Intent intent=new Intent(this, OrderDetailsActivity.class);
        intent.putParcelableArrayListExtra(getString(R.string.ordered_list),products);
        startActivity(intent);

    }
}
