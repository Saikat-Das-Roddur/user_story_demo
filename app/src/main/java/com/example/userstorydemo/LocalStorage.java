package com.example.userstorydemo;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    private static final LocalStorage localStorage = new LocalStorage();
    public static LocalStorage getInstance() {
        return localStorage;
    }
    private LocalStorage() { }

    private SharedPreferences getBaseConfig(Context context){
        return context.getSharedPreferences("artsicraft", Context.MODE_PRIVATE);
    }

    public void setData(Context context, String key, String data){
        getBaseConfig(context).edit().putString(key,data).apply();
    }

    public void setData(Context context, String key, int data){
        getBaseConfig(context).edit().putInt(key,data).apply();
    }

    public void setData(Context context, String key, float data){
        getBaseConfig(context).edit().putFloat(key,data).apply();
    }

    public void setData(Context context, String key, boolean data){
        getBaseConfig(context).edit().putBoolean(key,data).apply();
    }

    public String getStringData(Context context, String key){
        return getBaseConfig(context).getString(key,"");
    }

    public int getIntData(Context context, String key){
        return getBaseConfig(context).getInt(key,-1);
    }

    public float getFloatData(Context context, String key){
        return getBaseConfig(context).getFloat(key,-1.0f);
    }

    public boolean getBooleanData(Context context, String key){
        return getBaseConfig(context).getBoolean(key,false);
    }

    public void clearData(Context context, String key){
        getBaseConfig(context).edit().remove(key).apply();
    }

    public void clearAll(Context context){
        getBaseConfig(context).edit().clear().apply();
    }

    public void clearAllData(Context context, String key){
        getBaseConfig(context).edit().clear().apply();
    }

}
