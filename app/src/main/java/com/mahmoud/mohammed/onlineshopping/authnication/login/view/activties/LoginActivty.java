package com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.authnication.login.component.DaggerLoginComponent;
import com.mahmoud.mohammed.onlineshopping.authnication.login.component.LoginComponent;
import com.mahmoud.mohammed.onlineshopping.authnication.login.modules.LoginModule;
import com.mahmoud.mohammed.onlineshopping.authnication.login.presenter.LoginPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.interfaces.LoginView;
import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;
import com.mahmoud.mohammed.onlineshopping.base.BaseActivity;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.ui.home.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.utils.KeyboardUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivty extends BaseActivity implements LoginView {

    @BindView(R.id.email)
    EditText inputEmail;
    @BindView(R.id.password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    Button btnSignIn;
    @BindView(R.id.btn_signup)
    Button btnSignUp;
    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Inject
    public LoginPresnterImpl loginPresenter;
    LoginComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        if (SessionHelper.getUser() != null) {
            startActivity(new Intent(LoginActivty.this, HomeActivity.class));
            finish();
        }
        ButterKnife.bind(this);
        component = DaggerLoginComponent.builder().loginModule(new LoginModule(this))
                .build();
        component.injectLoginActivity(this);
        initUi();
    }

    private void initUi() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginPresenter.validateLoginFields()) {
                    KeyboardUtil.dismissKeyboard(LoginActivty.this);
                    loginPresenter.login();
                }

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivty.this,SignUpActivity.class));
            }
        });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void showErrMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showNoInternetMsg() {
        Toast.makeText(this, R.string.error_internet_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        showViews(progressBar);
    }

    @Override
    public void hideProgress() {
        hideViews(progressBar);
    }

    @Override
    public void setEmailError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setPasswordError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void navigateToHome() {
startActivity(new Intent(LoginActivty.this, HomeActivity.class));
    }

    @Override
    public String getEmail() {
        return inputEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return inputPassword.getText().toString();
    }
}
