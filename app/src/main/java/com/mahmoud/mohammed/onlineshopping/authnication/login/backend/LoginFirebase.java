package com.mahmoud.mohammed.onlineshopping.authnication.login.backend;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Mohammed on 11/20/2017.
 * <p>
 * login using firebase backend
 */

public class LoginFirebase {

    public static void login(String email, String password, OnSuccessListener<AuthResult> successListener, OnFailureListener failureListener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(successListener)
                .addOnFailureListener(failureListener);
    }
}
