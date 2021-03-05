package com.zxd.zisall.iconbadge

import android.app.Application
import android.app.Notification
import android.net.Uri
import android.os.Bundle


class HuaWeiModelImpl : IconBadgeNumModel {
    override fun setIconBadgeNum(
        context: Application,
        notification: Notification?,
        count: Int
    ): Notification? {
        val bunlde = Bundle()
        bunlde.putString("package", context.packageName) // com.test.badge is your package name

        bunlde.putString(
            "class",
            IconBadgeUtils.getInstance()?.getLaunchIntentForPackage(context)
        ) // com.test. badge.MainActivity is your apk main activity

        bunlde.putInt("badgenumber", count)
        context.contentResolver.call(
            Uri.parse("content://com.huawei.android.launcher.settings/badge/"),
            "change_badge",
            null,
            bunlde
        )
        return notification
    }

    /**
     * /**
     * 桌面图标角标
    */
    fun getLauncherPackageName(context: Context): String? {
    //获取ApplicationContext
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    val res = context.packageManager.resolveActivity(intent, 0)
    if (res.activityInfo == null) {
    // should not happen. A home is always installed.
    return null
    }
    return if (res.activityInfo.packageName == "android") {
    null
    } else {
    res.activityInfo.packageName
    }
    }

    /**
     * 华为
     *
     * <uses-permission android:name="com.huawei.android.launcher.permission.CHANGE_BADGE"/>
     *
     * package：应用包名
     * class：桌面图标对应的应用入口Activity类
     * badgenumber：角标数字
    */
    @Throws(Exception::class)
    fun setHUAWEIIconBadgeNum(context: Context, count: Int) {
    val bunlde = Bundle()
    bunlde.putString("package", context.packageName)
    bunlde.putString("class", getLauncherPackageName(context))
    bunlde.putInt("badgenumber", count)
    context.contentResolver.call(
    Uri.parse("content://com.huawei.android.launcher.settings/badge/"),
    "change_badge",
    null,
    bunlde
    )
    }

    /**
     * 小米
    */
    @Throws(java.lang.Exception::class)
    fun setXIAOMIIconBadgeNum(count: Int, notification: Notification): Notification? {
    val field: Field = notification.javaClass.getDeclaredField("extraNotification")
    val extraNotification: Any = field.get(notification)
    val method: Method = extraNotification.javaClass.getDeclaredMethod(
    "setMessageCount",
    Int::class.javaPrimitiveType
    )
    method.invoke(extraNotification, count)
    return notification
    }

    /**
     * 三星
    */
    @Throws(java.lang.Exception::class)
    fun setSAMSUNGIconBadgeNum(context: Context, count: Int) {
    val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
    intent.putExtra("badge_count", count)
    intent.putExtra("badge_count_package_name", context.packageName)
    intent.putExtra("badge_count_class_name", getLauncherPackageName(context))
    context.sendBroadcast(intent)
    }

    /**
     * Google
    */
    @Throws(java.lang.Exception::class)
    fun setGoogleIconBadgeNum(context: Context,count: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
    throw java.lang.Exception("ERROR_LAUNCHER_NOT_SUPPORT_" + "Google")
    }
    val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
    intent.putExtra("badge_count", count)
    intent.putExtra("badge_count_package_name", context.packageName)
    intent.putExtra(
    "badge_count_class_name",
    getLauncherPackageName(context)
    ) // com.test. badge.MainActivity is your apk main activity
    context.sendBroadcast(intent)
    }
     */

}