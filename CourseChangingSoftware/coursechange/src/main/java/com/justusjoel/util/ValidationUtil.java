package com.justusjoel.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean validEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    public static boolean validRegNo(String regNo) {
        return Pattern.matches("^[A-Z]{2}/\\d{3}/\\d{4}$", regNo);
    }
}
