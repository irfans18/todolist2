package com.irfans.todolist2.utils;

import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtil {
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setToken(String token){
        Log.e("testing", token);
        sharedPreferences.edit().putString("token", token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString("token", null);
    }

    public void clear(){
        sharedPreferences.edit().clear().apply();
    }
}
