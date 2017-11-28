package com.mahmoud.mohammed.onlineshopping.communication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mohammed on 16/11/2017.
 */

public class FireHelper {
    public static DatabaseReference getRequiredPath(String ...params){
        String ref= "";
        for (String node : params) {
            ref += "/"+node ;
        }
       return FirebaseDatabase.getInstance().getReference().child(ref);


    }
}
