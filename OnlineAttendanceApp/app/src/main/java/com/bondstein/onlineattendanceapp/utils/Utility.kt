package com.bondstein.onlineattendanceapp.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AlertDialog
import com.bondstein.onlineattendanceapp.R

object Utility {

    // Checks if device is connected to the internet
    fun hasInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Returns a Network object corresponding to the currently active default data network
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport or Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }

    fun showNoInternetDialog(context: Context, activity: Activity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.no_internet_connection))
        builder.setMessage(context.getString(R.string.try_again))
        builder.setNeutralButton(context.getString(R.string.ok)) { _, _ ->
            activity.finish()
        }
        builder.show().setCanceledOnTouchOutside(false)
    }
}