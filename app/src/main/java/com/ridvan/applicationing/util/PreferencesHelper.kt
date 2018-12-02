package com.ridvan.applicationing.util

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
class PreferencesHelper(private val context: Context) {

    fun saveLong(key: String, value: Long) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun loadLong(key: String): Long {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getLong(key, 0L)
    }
}