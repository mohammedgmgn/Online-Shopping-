package com.mahmoud.mohammed.onlineshopping.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.mahmoud.mohammed.onlineshopping.authnication.register.views.activites.SignUpActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by mohammed on 28/11/2017.
 */

public class DialogHelper {

public static String getBirthDateFormat(Calendar myCalendar){
    String myFormat = "MM/dd/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
return sdf.format(myCalendar.getTime());
}

}
