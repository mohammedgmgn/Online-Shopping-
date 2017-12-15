package com.mahmoud.mohammed.onlineshopping.ui.orders.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.presnter.ProductPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.view.ProductView;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.adapter.OrdersAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyOrdersFragment extends Fragment implements ProductView{
    @BindView(R.id.orders_recycler_view_id)
    RecyclerView recyclerView;
    @Inject
    ProductPresnterImpl presnter;
    @Inject
    OrdersAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HomeActivity) getActivity()).getHomeComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_my_orders, container, false);
        ButterKnife.bind(this, root);
               initUi();
        return root;
    }

    private void initUi() {
        presnter.setView(this);
        presnter.getOrders();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
    public void displayProductList(List<Product> products) {

    }

    @Override
    public void displayMyCartList(List<Product> products) {

    }

    @Override
    public void displayOrdersList(List<Order> orderList) {
        adapter.setOrdersList(orderList);
    }
}
