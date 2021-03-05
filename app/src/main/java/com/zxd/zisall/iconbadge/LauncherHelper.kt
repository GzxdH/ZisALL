package com.zxd.zisall.iconbadge

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.annotation.Nullable


class LauncherHelper {

    companion object {

        val GOOGLE = "google"
        val HUAWEI = "huawei"
        val MEIZU = "meizu"
        val XIAOMI = "xiaomi"
        val OPPO = "oppo"
        val VIVO = "vivo"
        val SAMSUNG = "samsung"

        val launcherMap = hashMapOf<String, String>(
            "com.huawei.android.launcher" to HUAWEI,
            "com.miui.home" to XIAOMI,
            "com.sec.android.app.launcher" to SAMSUNG,
            "com.google.android.apps.nexuslauncher" to GOOGLE
        )

    }

    @Nullable
    fun getLauncherTypeByName(launcherName: String): String? {
        return launcherMap[launcherName]
    }

    @Nullable
    fun getLauncherPackageName(context: Context): String? {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        val res: ResolveInfo = context.getPackageManager().resolveActivity(intent, 0)
        if (res.activityInfo == null) {
            // should not happen. A home is always installed, isn't it?
            return null
        }
        return if (res.activityInfo.packageName == "android") {
            null
        } else {
            res.activityInfo.packageName
        }
    }

}