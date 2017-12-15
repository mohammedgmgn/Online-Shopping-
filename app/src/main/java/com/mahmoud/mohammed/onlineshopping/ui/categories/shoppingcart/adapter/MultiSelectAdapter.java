package com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.adapters.MainAdapter;
import com.mahmoud.mohammed.onlineshopping.models.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jaison on 08/10/16.
 */

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MyViewHolder> {

    public ArrayList<Product> usersList=new ArrayList<>();
    public ArrayList<Product> selected_usersList=new ArrayList<>();
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public MultiSelectAdapter(Context context, ArrayList<Product> userList, ArrayList<Product> selectedList) {
        this.mContext=context;
        this.usersList = userList;
        this.selected_usersList = selectedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MultiSelectAdapter.MyViewHolder productHolder = (MultiSelectAdapter.MyViewHolder) (holder);
        Product product = usersList.get(position);
        productHolder.productName.setText(product.getProductName());
        productHolder.itemDescription.setText(product.getDescription());
        productHolder.itemPrice.setText(product.getPrice());
        Glide.with(mContext).load(product.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(productHolder.productIv);

        if(selected_usersList.contains(usersList.get(position)))
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.btn_logut_bg));
        else
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}

