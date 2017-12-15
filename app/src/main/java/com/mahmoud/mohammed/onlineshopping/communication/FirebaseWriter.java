package com.mahmoud.mohammed.onlineshopping.communication;

import com.google.android.gms.tasks.OnSuccessListener;
import com.mahmoud.mohammed.onlineshopping.models.Product;

/**
 * Created by mohammed on 11/21/2017.
 */

public class FirebaseWriter{

    //write with pushing key
    public static void writeWithAutoKey (Object object , OnSuccessListener onSuccessListener, String ... params ){
        FireHelper.getRequiredPath(params).push().setValue(object).addOnSuccessListener(onSuccessListener);
   }
   // write with customekey
    public static void writeWithCustomKey(Object object , OnSuccessListener onSuccessListener, String ... params ){
        FireHelper.getRequiredPath(params).setValue(object).addOnSuccessListener(onSuccessListener);
    }


   /* public static void addProductToCart(Product product, OnSuccessListener onSuccessListener)
   {
       FireHelper.getRequiredPath(FireConstants.MY_CARTS,SessionHelper.getUser().getUid())
               .setValue(product).addOnSuccessListener(onSuccessListener);
   }*/
}
