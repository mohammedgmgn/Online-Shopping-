package com.mahmoud.mohammed.onlineshopping.communication;

import android.webkit.ValueCallback;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by mohammed on 16/11/2017.
 */

public class FirebaseReader {
    public static void getFireDataList(final Class<?> obClass, final ValueCallback<ArrayList<Object>> callback, String ... params) {
        FireHelper.getRequiredPath(params)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass,dataSnapshot));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
    public static void getSnapShot(final ValueCallback<DataSnapshot> callback,String ... params){
        FireHelper.getRequiredPath(params).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onReceiveValue(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
  public static void getSearchResult(String keyWord,final Class<?> obClass, final ValueCallback<ArrayList<Object>> callback){

      Query query = FireHelper.getRequiredPath(FireConstants.PRODUCTS_NODE
              ,FireConstants.OFFERS_CHILD).orderByChild(FireConstants.PRODUCT_KEY).startAt(keyWord);
      query.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              if (dataSnapshot.exists()) {
                  callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass,dataSnapshot));
              }

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

  }

    public static void getSnapShotSinglieListner(final ValueCallback<DataSnapshot> callback,String ... params){

        FireHelper.getRequiredPath(params).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onReceiveValue(dataSnapshot);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void getFireDataSingleObject(final Class<?> obClass,final ValueCallback<Object>callback,String ... params){
        FireHelper.getRequiredPath(params)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(dataSnapshot.getValue(obClass));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }



}
