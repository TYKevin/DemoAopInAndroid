package com.example.myapplication.example_login.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.BaseApplication;

public class SharedPreferencesUtil {

    private static final String KEY_USER_SP_NAME = "USER_INFO";
    private static final String KEY_LOGINED_USERNAME = "LOGINED_USERNAME";

    private static SharedPreferences userSp;

    static {
        userSp = BaseApplication.getInstance().getSharedPreferences(KEY_USER_SP_NAME, Context.MODE_PRIVATE);
    }

    public static void saveLoginedUser(String username) {
        SharedPreferences.Editor editor = userSp.edit();
        editor.putString(KEY_LOGINED_USERNAME, username);
        editor.commit();
    }

    public static String getLoginedUser() {
        return userSp.getString(KEY_LOGINED_USERNAME, "");
    }

    public static void clearLoginUser() {
        SharedPreferences.Editor editor = userSp.edit();
        editor.remove(KEY_LOGINED_USERNAME);
        editor.commit();

    }
}
