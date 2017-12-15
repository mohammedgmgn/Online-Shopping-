package com.mahmoud.mohammed.onlineshopping.ui.orders.presnter;

import android.content.Context;
import android.content.Intent;
import android.webkit.ValueCallback;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.communication.FireConstants;
import com.mahmoud.mohammed.onlineshopping.communication.FirebaseReader;
import com.mahmoud.mohammed.onlineshopping.communication.FirebaseWriter;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.interfaces.OrderDetailsViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mohammed on 14/12/2017.
 */

public class OrderDetailsPresenterImp implements OrderDetailsPresenter {
    Context context;
    OrderDetailsViews detailsViews;
    private List<Product> orderList;
    private Order order;
    double TotalCost;

    @Inject
    public OrderDetailsPresenterImp(OrderDetailsActivity context) {
        this.context = context;
    }

    @Override
    public void setView(BaseView view) {
        detailsViews = (OrderDetailsViews) view;
    }

    @Override
    public void calculateTotalPrice() {
        //      List<Product> orderList = intent.getExtras().getParcelableArrayList(context.getString(R.string.ordered_list));
        detailsViews.displayTotalPrice(getTotalCost());
    }

    private double getTotalCost() {
        double totalPrice = 0;
        for (int i = 0; i < orderList.size(); i++) {
            totalPrice += (Double.parseDouble(orderList.get(i).getPrice())) * (orderList.get(i).getQuantity());
        }
        TotalCost=totalPrice;
        return totalPrice;
    }

    @Override
    public void displayOrderdList() {
        //  List<Product> orderList = intent.getExtras().getParcelableArrayList(context.getString(R.string.ordered_list));
        detailsViews.displayOrderdList(orderList);
    }

    @Override
    public void removeOrderedItem(int position) {
        //    List<Product> orderList = intent.getExtras().getParcelableArrayList(context.getString(R.string.ordered_list));
        orderList.remove(position);
        detailsViews.displayOrderdList(orderList);

    }

    @Override
    public void setPresenterData(Intent intent) {
        orderList = intent.getExtras().getParcelableArrayList(context.getString(R.string.ordered_list));
    }

    @Override
    public void editOrderedItem(Product product) {
        for (int i = 0; i < orderList.size(); i++) {
            if ((orderList.get(i).getId()) == product.getId()) {
                orderList.remove(i);
                orderList.add(product);
            }
        }
        detailsViews.displayOrderdList(orderList);

                       /*    Firebase ref = mFirebaseRef.child(FirebaseReference.CHILD_FLIGHTS);
                    Query queryRef = ref.orderByChild("arrivalDate_code").equalTo("2016-06-21_GOT");
                    queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                        }
                    });*/


    }

    @Override
    public void confirmOrderPressed(String address) {
        if (orderList.size() == 0) {
            detailsViews.showListEmptyError();
        } else {

            FirebaseReader.getSnapShotSinglieListner(new ValueCallback<DataSnapshot>() {
                @Override
                public void onReceiveValue(DataSnapshot dataSnapshot) {
                    String orderID;
                    if (dataSnapshot == null) {
                        orderID = String.valueOf(1);
                    } else {
                        orderID = String.valueOf(dataSnapshot.getChildrenCount() + 1);
                    }
                    order = new Order(TotalCost,orderID, SessionHelper.getUser().getUid(), address, getCurrentDate());
                    FirebaseWriter.writeWithAutoKey(order, new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            detailsViews.showOrderSuccessMessage();
                        }
                    }, FireConstants.ORDER_NODE, SessionHelper.getUser().getUid());

                }
            }, FireConstants.ORDER_NODE, SessionHelper.getUser().getUid());
         /*   FirebaseReader.getFireDataList(Order.class, new ValueCallback<ArrayList<Object>>() {
                @Override
                public void onReceiveValue(ArrayList<Object> objects) {
                    String orderID;
                    if (objects == null) {
                        orderID = String.valueOf(1);
                    } else {
                        orderID = String.valueOf(objects.size() + 1);
                    }
                        order = new Order(orderID, SessionHelper.getUser().getUid(), address, getCurrentDate());
                        FirebaseWriter.writeWithAutoKey(order, new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                detailsViews.showOrderSuccessMessage();
                            }
                        }, FireConstants.ORDER_NODE, SessionHelper.getUser().getUid());

                    }


            }, FireConstants.ORDER_NODE, SessionHelper.getUser().getUid());

*/
        }
    }

    private String getCurrentDate() {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;

    }
}
