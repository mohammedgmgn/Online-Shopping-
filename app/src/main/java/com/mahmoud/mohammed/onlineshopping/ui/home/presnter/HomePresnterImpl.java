package com.mahmoud.mohammed.onlineshopping.ui.home.presnter;

import android.content.Context;
import android.webkit.ValueCallback;

import com.google.firebase.database.DataSnapshot;
import com.mahmoud.mohammed.onlineshopping.base.BaseView;
import com.mahmoud.mohammed.onlineshopping.communication.FireConstants;
import com.mahmoud.mohammed.onlineshopping.communication.FirebaseReader;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivityView;

import javax.inject.Inject;

/**
 * Created by mohammed on 10/12/2017.
 */

public class HomePresnterImpl implements HomePresnter {
    HomeActivityView homeActivityView;
    Context context;

    @Override
    public void setView(BaseView view) {
        homeActivityView = (HomeActivityView) view;
    }

    @Inject
    public HomePresnterImpl(HomeActivity context) {
        this.context = context;
    }

    @Override
    public void getMyCartListCount() {
        FirebaseReader.getSnapShot(new ValueCallback<DataSnapshot>() {
            @Override
            public void onReceiveValue(DataSnapshot dataSnapshot) {
                 homeActivityView.displayproductCartCount((int)dataSnapshot.getChildrenCount());
            }
        }, FireConstants.MY_CARTS, SessionHelper.getUser().getUid());

    }

}
