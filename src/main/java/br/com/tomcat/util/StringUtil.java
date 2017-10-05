package br.com.tomcat.util;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public class StringUtil {

    public static boolean isNull(final String str) {
        return str == null;
    }

    public static boolean nonNull(final String str) {
        return !isNull(str);
    }

    public static boolean isNullEmpty(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean nonNullEmpty(final String str) {
        return !isNullEmpty(str);
    }

    public static String onlyNumbers(final String str) {
        return isNullEmpty(str) ? "" : str.replaceAll("[^0-9]", "");
    }

    public static String formatCPF(final String str) {
        return isNullEmpty(str) ? "" : str.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4");
    }

    public static String formatTel(final String str) {
        return isNullEmpty(str) ? "" : str.length() == 11 ? str.replaceAll("([0-9]{3})([0-9]{4})([0-9]{4})", "($1) $2-$3") : str.replaceAll("([0-9]{3})([0-9]{1})([0-9]{4})([0-9]{4})", "($1) $2 $3-$4");
    }

    public static String formatRealMoney(final Double value) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(value);
    }

    public static String getPasswordRandom() {
        return UUID.randomUUID().toString().substring(0,6);
    }

}
