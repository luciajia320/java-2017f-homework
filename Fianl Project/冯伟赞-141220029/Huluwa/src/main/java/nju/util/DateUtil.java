package nju.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("(MM-dd HH:mm:ss)");

    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static String formatDate(Date date, String pattern) {
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

}
