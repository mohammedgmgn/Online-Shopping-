package com.mahmoud.mohammed.onlineshopping.utils.notification;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.MenuItem;

/**
 * Created by Mohammed on 30-11-2017.
 */
public class NotificationCountSetClass extends Activity {
    private static LayerDrawable icon;
    public NotificationCountSetClass() {
        //constructor
    }

    public static void setAddToCart(Context context, MenuItem item, int numMessages) {
        icon = (LayerDrawable) item.getIcon();
        SetNotificationCount.setBadgeCount(context, icon, NotificationCountSetClass.setNotifyCount(numMessages));

    }

    public static int setNotifyCount(int numMessages) {
        int count=numMessages;
        return count;

    }


}
