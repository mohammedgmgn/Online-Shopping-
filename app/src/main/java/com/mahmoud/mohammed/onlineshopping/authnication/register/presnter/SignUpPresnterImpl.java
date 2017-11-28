package com.mahmoud.mohammed.onlineshopping.authnication.register.presnter;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.authnication.register.backend.RegistrationFirebase;
import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;
import com.mahmoud.mohammed.onlineshopping.authnication.register.views.interfaces.SignUpView;
import com.mahmoud.mohammed.onlineshopping.models.User;
import com.mahmoud.mohammed.onlineshopping.utils.EmailValidator;

import javax.inject.Inject;

/**
 * Created by mohammed on 28/11/2017.
 */

public class SignUpPresnterImpl implements SignUpPresnter, OnSuccessListener<AuthResult>, OnFailureListener {
    private User user;
    private SignUpView view;
    private SignUpActivity mContext;

    @Inject
    public SignUpPresnterImpl(SignUpActivity context) {
        mContext = context;
        setView(context);
    }

    @Override
    public void register() {
        view.showProgress();
        RegistrationFirebase.register(view.getEmail(), view.getPassword(), this, this);
    }

    @Override
    public void setView(SignUpActivity view) {
        this.view = view;
    }

    @Override
    public boolean validateSignUpFields() {
        if (view.getName().isEmpty()) {
            view.showMessage(mContext.getString(R.string.error_name_empty));
            return false;
        } else if (view.getEmail().isEmpty()) {
            view.setEmailError(mContext.getString(R.string.email));
            return false;
        } else if (!view.getEmail().isEmpty() && !EmailValidator.isValid(view.getEmail())) {
            view.setEmailError(mContext.getString(R.string.enter_valid_email));
            return false;
        } else if (view.getBirthDate().isEmpty()) {
            view.showMessage(mContext.getString(R.string.error_birthdate_empty));
            return false;
        } else if (view.getPassword().isEmpty()) {
            view.setPasswordError(mContext.getString(R.string.enter_your_password));
            return false;
        } else if (view.getPassword().length() < 6) {
            view.setPasswordError(mContext.getString(R.string.minimum_password));
            return false;
        } else if (!view.getPassword().equals(view.getConfirmedPassword())) {
            view.showMessage(mContext.getString(R.string.error_confirm_password));
            return false;

        }
        return true;
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        view.showMessage(e.getLocalizedMessage());
    }

    @Override
    public void onSuccess(AuthResult authResult) {
        user = User.createNewUser(view.getEmail(), view.getName(), view.getBirthDate());
        //TODO writ user in FireDB
        view.hideProgress();
        view.navigateToHome();
    }
}
