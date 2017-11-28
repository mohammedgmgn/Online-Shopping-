package com.mahmoud.mohammed.onlineshopping.authnication.register.backend;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Mohammed on 11/22/2017.
 * this class to sign up with firebase
 */

public class RegistrationFirebase {

    public static  void register(String email, String password, OnSuccessListener<AuthResult> successListener, OnFailureListener failureListener){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(successListener)
                .addOnFailureListener(failureListener);
    }
}
