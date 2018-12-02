package com.ridvan.applicationing.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
class ConnectionHelper(private val context: Context) {

    fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}