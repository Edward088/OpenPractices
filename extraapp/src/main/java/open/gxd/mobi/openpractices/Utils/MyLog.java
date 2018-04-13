package open.gxd.mobi.openpractices.Utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

import open.gxd.mobi.openpractices.BuildConfig;

/**
 * Created by edward.ge on 2018/3/8.
 */

public class MyLog {
    private static final String TAG = "op";

    public static void i(Object o) {
        //if (App.APP_DEBUG) {
        Log.i(TAG, String.valueOf(o));
        //}
    }

    public static void d(Object o) {
        if (BuildConfig.DEV_MODE) {
            Log.d(TAG, String.valueOf(o));
        }
    }

    public static void e(Object o) {
        //if (DEBUG) {
        Log.e(TAG, String.valueOf(o));
        //}
    }

    public static void e(String tag, Object e) {

        Log.e(TAG, tag + " error:" + String.valueOf(e));
    }

    public static void e(String o,Throwable t) {
        Log.e(TAG, o + "\n" + printError(t));
    }

    public static void w(Object o) {
        Log.w(TAG, String.valueOf(o));
    }

    public static void w(Object o,Throwable t) {
        //if (DEBUG) {
        Log.w(TAG, String.valueOf(o) + "\n" + printError(t));
        //}
    }

    public static void v(Object o) {
        if (BuildConfig.DEV_MODE) {
            Log.v(TAG, String.valueOf(o));
        }
    }

    public static void d(Object... os) {
        if (BuildConfig.DEV_MODE) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Object o: os) {
                stringBuilder.append(String.valueOf(o)).append(" ");
            }
            Log.d(TAG, stringBuilder.toString());
        }
    }

    public static void i(String tag, Object s) {
        if (BuildConfig.DEV_MODE) {
            Log.i(TAG, tag + " " + String.valueOf(s));
        }
    }

    private static String printError(Throwable throwable) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        return writer.toString();
    }
}
