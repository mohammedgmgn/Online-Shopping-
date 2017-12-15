package com.mahmoud.mohammed.onlineshopping.base.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mahmoud.mohammed.onlineshopping.MainActivity;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.eventbus.ItemProductClicked;
import com.mahmoud.mohammed.onlineshopping.eventbus.ProductItemClicked;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.utils.ImageUrlUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed on 29/11/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Product> productList;
    Context context;
    List<Product> myCartList;


    private onProductClickListener onProductClickListener;

    public void setOnProductClickListener(onProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    @Inject
    public MainAdapter(HomeActivity context) {
        productList = new ArrayList<>();
        myCartList=new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ProductHolder holder = new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) (holder);
        Product product = productList.get(position);
        productHolder.productName.setText(product.getProductName());
        productHolder.itemDescription.setText(product.getDescription());
        productHolder.itemPrice.setText(product.getPrice());
        Glide.with(context).load(product.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(productHolder.productIv);


        productHolder.favoritIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.itemView.setOnClickListener(view -> onProductClickListener.onProcductClicked(product));
            //check cart list for editing cart list
            /*  if(myCartList.size()>0)
              {
                  productHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                      @Override
                      public boolean onLongClick(View view) {
                          EventBus.getDefault().post(new ItemProductClicked(product));
                          return false;
                      }
                  });

              }*/

    }

    public void setProductsList(List<Product> products) {
        this.productList.clear();
        this.productList.addAll(products);
        this.notifyDataSetChanged();
    }
    public void setMyCartList(List<Product> products) {
        this.productList.clear();
        this.productList.clear();
        this.productList.addAll(products);

        this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return productList.size();
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
