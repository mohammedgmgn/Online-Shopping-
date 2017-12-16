package com.mahmoud.mohammed.onlineshopping.ui.categories.products.offers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.adapters.MainAdapter;
import com.mahmoud.mohammed.onlineshopping.base.adapters.onProductClickListener;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.presnter.ProductPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.view.ProductView;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.DetailActivity;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OffersFragment extends Fragment implements ProductView, onProductClickListener{
    @BindView(R.id.offers_recycler_view_id)
    RecyclerView recyclerView;
    @Inject
    ProductPresnterImpl presnter;
    @Inject
    MainAdapter adapter;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HomeActivity) getActivity()).getHomeComponent().inject(this);
        setHasOptionsMenu(true);
        mContext = getActivity();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_offers, container, false);
        ButterKnife.bind(this, root);
        presnter.setView(this);
        initUi();
        return root;
    }

    private void initUi() {
        presnter.getOffersProductList();
        adapter.setOnProductClickListener(this);
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
        adapter.setProductsList(products);

    }

    @Override
    public void displayMyCartList(List<Product> products) {

    }

    @Override
    public void displayOrdersList(List<Order> orderList) {

    }


    @SuppressLint("StringFormatInvalid")
    @Override
    public void onProcductClicked(Product product) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(getString(R.string.product_key), product);
        startActivity(intent);
    }




    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(String item);
    }


}
