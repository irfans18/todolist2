package com.irfans.todolist2.utils.SharedPreferences;

import android.content.SharedPreferences;

public class TokenSessionRepository {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public TokenSessionRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setToken(String token){
        sharedPreferences.edit().putString("token", token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString("token", null);
    }

    public void clear(){
        editor.putString("token", null).apply();
    }
}
