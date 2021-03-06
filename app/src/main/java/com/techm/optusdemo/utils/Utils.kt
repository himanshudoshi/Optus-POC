package com.techm.optusdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/** Util Class for Network Connectivity */
class Utils {

    companion object {

        fun hasNetwork(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }
}