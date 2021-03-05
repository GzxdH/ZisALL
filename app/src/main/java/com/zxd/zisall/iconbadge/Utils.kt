package com.zxd.zisall.iconbadge

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager


class Utils {
    companion object{

        val UNABLE_TO_RESOLVE_INTENT_ERROR_ = "unable to resolve intent: "
        private var instance: Utils? = null

        fun getInstance(): Utils? {
            if (instance == null) {
                instance = Utils()
            }
            return instance
        }

    }

    fun canResolveBroadcast(context: Context, intent: Intent?): Boolean {
        val packageManager: PackageManager = context.packageManager
        val receivers =
            packageManager.queryBroadcastReceivers(intent, 0)
        return receivers != null && receivers.size > 0
    }

    fun getLaunchIntentForPackage(context: Context): String? {
        return context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?.component?.className
    }
}