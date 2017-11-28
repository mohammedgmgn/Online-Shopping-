package com.mahmoud.mohammed.onlineshopping.communication;

import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by mohammed on 11/21/2017.
 */

public class FirebaseWriter  {
    public static void write (Object object , OnSuccessListener onSuccessListener, String ... params ){
        FireHelper.getRequiredPath(params).setValue(object).addOnSuccessListener(onSuccessListener);
   }
}
