package com.baharudin.bwamov.utils

import android.content.Context
import android.content.SharedPreferences

//Membuat user preference
class Preferences (val context: Context){
    companion object{
        const val USER_PREF ="USER_PREF"
    }
    val sharedPreferences = context.getSharedPreferences(USER_PREF,0)

    fun setValue(key : String, value : String){
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun getValue(key: String) : String?{
        return sharedPreferences.getString(key, "")
    }
}