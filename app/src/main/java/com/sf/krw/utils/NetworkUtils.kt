package com.sf.krw.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/04/21
 */

class NetworkUtils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkManager = connectivityManager.activeNetwork ?: return false
                val currentActiveNetwork = connectivityManager.getNetworkCapabilities(networkManager) ?: return false
                return when {
                    currentActiveNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    currentActiveNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } else {
                return connectivityManager.activeNetworkInfo?.isConnected ?: false
            }
        }
    }
}