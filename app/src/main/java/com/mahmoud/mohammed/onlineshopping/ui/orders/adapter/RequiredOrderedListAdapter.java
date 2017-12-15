package com.mahmoud.mohammed.onlineshopping.ui.orders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.adapters.onOrderedProductClicked;
import com.mahmoud.mohammed.onlineshopping.base.adapters.onProductClickListener;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed on 14/12/2017.
 */

public class RequiredOrderedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Product> orderedList;
    Context context;
    private onOrderedProductClicked onProductClickListener;

    public void setOnProductClickListener(onOrderedProductClicked onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    @Inject
    public RequiredOrderedListAdapter(OrderDetailsActivity context) {
        orderedList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderd_product_item, parent, false);
        ProductHolder holder = new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) (holder);
        Product product = orderedList.get(position);
        Glide.with(context).load(product.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(productHolder.productIv);
        productHolder.quantityTv.setText(String.valueOf(product.getQuantity()));
        productHolder.itemView.setOnClickListener(view -> onProductClickListener.onOrderedProcductClicked(product,position));


    }

    @Override
    public int getItemCount() {
        return orderedList.size();
    }
    public void setProductsList(List<Product> products) {
        this.orderedList.clear();
        this.orderedList.addAll(products);
        this.notifyDataSetChanged();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_orderd_image)
        ImageView productIv;
        @BindView(R.id.item_quantity)
        TextView quantityTv;

        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
