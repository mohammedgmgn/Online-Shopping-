package com.mahmoud.mohammed.onlineshopping.communication;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by mohammed on 16/11/2017.
 */

public class DataFetcher {
    public static ArrayList<Object> getListFromSnapShot(final Class<?> obClass,DataSnapshot dataSnapshot) {
        ArrayList<Object> list = new ArrayList<>();
        for (DataSnapshot dataShot : dataSnapshot.getChildren()){
            list.add(dataShot.getValue(obClass));
        }
        return list;
    }

}
