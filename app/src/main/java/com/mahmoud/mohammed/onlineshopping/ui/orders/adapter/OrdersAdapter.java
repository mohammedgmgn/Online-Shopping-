package com.mahmoud.mohammed.onlineshopping.ui.orders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed on 15/12/2017.
 */

public class OrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Order> orders;
    Context context;
    ///  private onOrderedProductClicked onProductClickListener;

   /* public void setOnProductClickListener(onOrderedProductClicked onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }*/

    @Inject
    public OrdersAdapter(HomeActivity context) {
        orders = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);

        ProductHolder holder = new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) (holder);
        Order order = orders.get(position);
        productHolder.orderCountTv.setText(order.getOrderId());
        productHolder.priceTv.setText(String.valueOf(order.getTotalCost()));
        //  productHolder.itemView.setOnClickListener(view -> onProductClickListener.onOrderedProcductClicked(product,position));


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrdersList(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
        this.notifyDataSetChanged();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.total_price_tv_id)
        TextView priceTv;
        @BindView(R.id.order_count_tv_id)
        TextView orderCountTv;

        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
