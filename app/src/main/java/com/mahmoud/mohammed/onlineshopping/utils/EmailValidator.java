package com.mahmoud.mohammed.onlineshopping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aymanabouzeid on 29/12/16.
 */

public class EmailValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
