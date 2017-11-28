package com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;
import com.mahmoud.mohammed.onlineshopping.authnication.register.component.DaggerSignUpComponent;
import com.mahmoud.mohammed.onlineshopping.authnication.register.component.SignUpComponent;
import com.mahmoud.mohammed.onlineshopping.authnication.register.modules.SignUpModule;
import com.mahmoud.mohammed.onlineshopping.authnication.register.presnter.SignUpPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.authnication.register.views.interfaces.SignUpView;
import com.mahmoud.mohammed.onlineshopping.base.BaseActivity;
import com.mahmoud.mohammed.onlineshopping.ui.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.utils.DialogHelper;
import com.mahmoud.mohammed.onlineshopping.utils.KeyboardUtil;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity implements SignUpView {
    Calendar myCalendar;
    @BindView(R.id.name_ed)
    EditText nameEd;

    @BindView(R.id.birthday)
    EditText birthDayEd;

    @BindView(R.id.email_ed)
    EditText emailEt;

    @BindView(R.id.password_ed)
    EditText passwordEd;

    @BindView(R.id.confirm_password_ed)
    EditText confirmPasswordEd;


    @BindView(R.id.sign_up_button)
    Button registerBtn;

    @BindView(R.id.btn_reset_password_btn)
    Button forgotPasswordBtn;
    @BindView(R.id.sign_in_button)
    Button signInBtn;
    @BindView(R.id.progressBar_view)
    ProgressBar progressBar;
    @Inject
    SignUpPresnterImpl presnter;
    SignUpComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        component = DaggerSignUpComponent.builder().signUpModule(new SignUpModule(this))
                .build();
        component.injectSignUpActivity(this);

        initUi();
    }

    private void initUi() {
        myCalendar = Calendar.getInstance();
        birthDayEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBirthDateDialog();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presnter.validateSignUpFields())
                {
                    KeyboardUtil.dismissKeyboard(SignUpActivity.this);
                    presnter.register();
                }
            }
        });
    }

    private void showBirthDateDialog() {
        new DatePickerDialog(SignUpActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        birthDayEd.setText(DialogHelper.getBirthDateFormat(myCalendar));
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
    public void setEmailError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setPasswordError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginFail(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public String getEmail() {
        return emailEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEd.getText().toString();
    }

    @Override
    public String getConfirmedPassword() {
        return confirmPasswordEd.getText().toString();
    }

    @Override
    public String getBirthDate() {
        return birthDayEd.getText().toString();
    }

    @Override
    public String getName() {
        return nameEd.getText().toString();
    }
}
