package com.example.userloginkotlinapplication.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.example.userloginkotlinapplication.service.model.LoginModeldata

class MySharedpreference(context : Context) {

    private val PREFS_NAME = "SharedPreferenceDemo"
    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveString(key: String?, value: String?) {
        val editor: Editor = preferences.edit()
        editor.putString(key, value)
        editor.commit()
    }



    fun setint(key: String?, value: Int) {
        val editor: Editor = preferences.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun setboolean(key: String?, value: Boolean?) {
        val editor: Editor = preferences.edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }

    fun setFloat(key: String?, value: Float?) {
        val editor: Editor = preferences.edit()
        editor.putFloat(key, value!!)
        editor.commit()
    }

    fun setLong(key: String?, value: Long?) {
        val editor: Editor = preferences.edit()
        editor.putLong(key, value!!)
        editor.commit()
    }


    fun getint(Key: String?): Int {
        return preferences.getInt(Key, 3)
    }

    fun getString(key: String?): String? {
        return preferences.getString(key, null)
    }

    fun getIsCheck(key: String?): Boolean {
        return preferences.getBoolean(key, false)
    }


}