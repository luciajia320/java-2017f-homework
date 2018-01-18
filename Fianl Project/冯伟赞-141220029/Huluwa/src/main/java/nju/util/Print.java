package nju.util;

import nju.constant.Values;

import java.io.PrintStream;

public class Print {

    // Print with a newline:
    public static void print(Object obj) {
        System.out.println(Values.AnsiColor.ANSI_RESET.getColor() + "" + obj);
    }

    /**
     * output text to console with color
     * @param color text color
     * @param object text output
     */
    public static void print(Values.AnsiColor color, Object object) {
        System.out.println(color.getColor() + "" + object);
    }

    /**
     * output text to console while prefix without color and suffix with color
     * @param prefix text without color
     * @param color text color
     * @param suffix text with color
     */
    public static void print(Object prefix, Values.AnsiColor color, Object suffix) {
        System.out.println(Values.AnsiColor.ANSI_RESET.getColor() + "" + prefix
                + color.getColor() + "" + suffix);
    }


    // Print with no line break:
    public static void printnb(Object obj) {
        System.out.print(Values.AnsiColor.ANSI_RESET.getColor() + "" + obj);
    }
    // The new Java SE5 printf() (from C):
    public static PrintStream
    printf(String format, Object... args) {
        return System.out.printf(Values.AnsiColor.ANSI_RESET.getColor() + "" + format, args);
    }
}
