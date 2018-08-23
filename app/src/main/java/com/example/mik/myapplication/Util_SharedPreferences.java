package com.example.mik.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Util_SharedPreferences
{
    private SharedPreferences f_sharedPreferences;

    public Util_SharedPreferences(Activity p_activity)
    {
        f_sharedPreferences = p_activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getSharedPreferences(String p_string_id)
    {
        return f_sharedPreferences.getString("KEY", null);
    }

    public void setSharedPreferences(String p_string_id)
    {
        f_sharedPreferences.edit().putString("KEY", p_string_id).commit();
    }
}
