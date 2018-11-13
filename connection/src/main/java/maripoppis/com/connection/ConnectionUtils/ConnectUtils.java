/*
 * Copyright (c) Developed by John Alves at 2018/10/29.
 */

package maripoppis.com.connection.ConnectionUtils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.util.Log;

import maripoppis.com.connection.BuildConfig;
import maripoppis.com.connection.Model.WSResponse;
import retrofit2.Response;

public class ConnectUtils {

    @SuppressLint("LongLogTag")
    public static void logFunction(Response<WSResponse> response, String conectDescription) {
        Log.w("Connect With = " + conectDescription, "OK");
        int http_status = response.code();
        if (http_status < 300) {
            logW("Http Status OK", String.valueOf(http_status));
        } else if (http_status >= 300 || http_status < 399) {
            logE("Http Status", String.valueOf(http_status));
        } else {
            logE("Http Status Error", String.valueOf(http_status));
        }
    }

    public static void logFunction(Throwable t, String conectDescription) {
        Log.e("Connect " + conectDescription, "Fail");
        Log.e("Error", t.getMessage());
        Log.e("Message", t.getLocalizedMessage());
    }

    //==============================================================================================
    //
    //
    //
    //==============================================================================================
    public static void logW(String tag, String msg) {
        if (statusRelise()) {
            Log.w(tag, msg);
        }
    }

    public static void logE(String tag, String msg) {
        if (statusRelise()) {
            Log.e(tag, msg);
        }
    }

    private static boolean statusRelise() {
        return BuildConfig.DEBUG;
    }
}
