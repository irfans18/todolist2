package com.irfans.todolist2.utils.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.irfans.todolist2.utils.SharedPreferences.UserSharedUtil;

public class UtilProvider {
    private static TokenSharedUtil tokenSharedUtil;
    private static UserSharedUtil userSharedUtil;

    public static void initialize(Context context){
        initSharedPreferencesUtil(context);
    }

    private static void initSharedPreferencesUtil(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("example", Context.MODE_PRIVATE);
        tokenSharedUtil = new TokenSharedUtil(sharedPreferences);
        userSharedUtil = new UserSharedUtil(sharedPreferences);
    }

    public static TokenSharedUtil getTokenSharedUtil(){
        return tokenSharedUtil;
    }

    public static UserSharedUtil getUserSharedUtil(){
        return userSharedUtil;
    }

}
