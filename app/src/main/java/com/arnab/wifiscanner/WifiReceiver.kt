package com.arnab.wifiscanner

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.util.Log

internal class WifiReceiver(private val wifiManager: WifiManager) : BroadcastReceiver() {

    private var wifiList: List<ScanResult>? = null
    private var sb = StringBuilder()

    @SuppressLint("MissingPermission")
    override fun onReceive(c: Context, intent: Intent) {
        sb = StringBuilder()
        wifiList = wifiManager.scanResults!!
        if (wifiList != null) {
            for (i in 0 until wifiList!!.size) {
                sb.append("${(i + 1)}.")
                sb.append(wifiList!![i].SSID)
                sb.append(" | ")
                sb.append("${wifiList!![i].level} db ")
                sb.append("(${getWifiStrength(wifiList!![i].level)})")
                sb.append("\n")
            }
        }

        // Update the data layer here
        Repository.updateData("WIFI List:\n\n${sb.toString()}")

        Log.d("WIFI: ", sb.toString())
    }

    private fun getWifiStrength(db: Int): String {

        return when {
            db >= -30 -> "Unbelievable"
            db >= -50 -> "Outstanding"
            db >= -60 -> "Excellent"
            db >= -67 -> "Very Good"
            db >= -70 -> "Good"
            db >= -80 -> "Bad"
            db >= -90 -> "Poor"
            else -> "very poor"
        }
    }
}