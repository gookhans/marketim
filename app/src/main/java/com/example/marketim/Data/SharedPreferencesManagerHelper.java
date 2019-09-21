package com.example.marketim.Data;

import android.content.Context;
import android.content.SharedPreferences;
public class SharedPreferencesManagerHelper {

    private static final String APP_SETTINGS = "App_Settings";
    private SharedPreferencesManagerHelper() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }
    public static void setRememberMe(Context context) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("remember", "true");
        editor.apply();
    }
    public static String getRememberMe(Context context) {
        return getSharedPreferences(context).getString("remember", null);
    }
}
