package com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.adapters.MainAdapter;
import com.mahmoud.mohammed.onlineshopping.base.adapters.onProductClickListener;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed on 13/12/2017.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Product> productCartList;
    Context context;
    private onProductClickListener onProductClickListener;

    public void setOnProductClickListener(onProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    @Inject
    public ShoppingCartAdapter(HomeActivity context) {
        this.productCartList = productCartList;
        this.context = context;
        this.onProductClickListener = onProductClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ProductHolder holder = new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
    public void setProductsList(List<Product> products) {
        this.productCartList.clear();
        this.productCartList.addAll(products);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productCartList.size();
    }


    public class ProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productIv;
        @BindView(R.id.ic_wishlist)
        ImageView favoritIv;
        @BindView(R.id.item_name)
        TextView productName;
        @BindView(R.id.item_description)
        TextView itemDescription;
        @BindView(R.id.item_price)
        TextView itemPrice;

        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
