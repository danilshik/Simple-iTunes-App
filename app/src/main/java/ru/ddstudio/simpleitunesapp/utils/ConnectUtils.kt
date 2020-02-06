package ru.ddstudio.simpleitunesapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log

object ConnectUtils {
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        }

        else {

            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo;
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    Log.i("update_status", "Network is available : true")
                    return true
                }
            } catch (e: Exception) {
                Log.i("update_status", "" + e.toString())
            }
        }
        Log.i("update_status","Network is available : FALSE ")
        return false
    }

}