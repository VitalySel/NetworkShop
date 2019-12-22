package com.seliverstov.shop.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceController {

    static public boolean checkPunct(String exp) {
        Pattern p = Pattern.compile("\\p{Punct}");
        Matcher m = p.matcher(exp);

        if (m.find()) return false;
        else return true;
    }

    static public boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("/^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$/");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    static public boolean checkPrice(String price) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(price);
        if (!m.matches()) {
            return false;
        }
        else return true;
    }
    static public boolean CheckPriceNegative(String price) {
        if (checkPrice(price)) {
            double parsePrice = Double.parseDouble(price);
            if (parsePrice < 0 || parsePrice == 0) {
                return false;
            }
        }
        return false;
    }
}
