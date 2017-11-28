package com.mahmoud.mohammed.onlineshopping.communication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mohammed on 16/11/2017.
 */

public class SessionHelper {
    public static FirebaseUser getUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

}
