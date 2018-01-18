package nju.util;


import nju.constant.GameParams;
import nju.constant.Values;

import java.util.Calendar;

public final class Log {

    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    private static final int LOG_LEVEL = GameParams.LOG_LEVEL;

    private Log() {
    }

    /**
     * Send a {@link #VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (LOG_LEVEL <= VERBOSE) {
            println(VERBOSE, tag, msg);
        }
    }

    /**
     * Send a {@link #DEBUG} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (LOG_LEVEL <= DEBUG) {
            println(DEBUG, tag, msg);
        }

    }

    /**
     * Send an {@link #INFO} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        if (LOG_LEVEL <= INFO) {
            println(INFO, tag, msg);
        }

    }

    /**
     * Send a {@link #WARN} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        if (LOG_LEVEL <= WARN) {
            println(WARN, tag, msg);
        }

    }


    /**
     * Send an {@link #ERROR} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        if (LOG_LEVEL <= LOG_LEVEL) {
            println(ERROR, tag, msg);
        }
    }

    public static void println(int level, String tag, String msg) {
        String time = DateUtil.formatDate(Calendar.getInstance().getTime());
        switch (level) {
            case VERBOSE:
                Print.print(time, Values.AnsiColor.ANSI_WHITE, " [" + tag + "] " + msg);
                break;
            case DEBUG:
                Print.print(time, Values.AnsiColor.ANSI_BLUE, " [" + tag + "] " + msg);
                break;
            case INFO:
                Print.print(time, Values.AnsiColor.ANSI_GREEN, " [" + tag + "] " + msg);
                break;
            case WARN:
                Print.print(time, Values.AnsiColor.ANSI_YELLOW, " [" + tag + "] " + msg);
                break;
            case ERROR:
                Print.print(time, Values.AnsiColor.ANSI_RED, " [" + tag + "] " + msg);
                break;
            case ASSERT:
                break;
        }
    }


}
