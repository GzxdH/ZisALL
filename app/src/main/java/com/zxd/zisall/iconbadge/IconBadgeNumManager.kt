package com.zxd.zisall.iconbadge

import android.app.Application
import android.app.Notification

class IconBadgeNumManager : IconBadgeNumModel {

    companion object {
        private const val NOT_SUPPORT_PHONE =
            "not support your phone [ Build.MANUFACTURER is null ]"
        private const val NOT_SUPPORT_MANUFACTURER_ = "not support "
        private const val NOT_SUPPORT_LAUNCHER =
            "not support your launcher [ default launcher is null ]"
        private const val NOT_SUPPORT_LAUNCHER_ = "not support "
    }

    private var iconBadgeNumModelMap: Map<String, IconBadgeNumModel>? =
        null
    private var launcherHelper: LauncherHelper? = null

    fun IconBadgeNumManager() {
        launcherHelper = LauncherHelper()
        iconBadgeNumModelMap = HashMap()
    }

    override fun setIconBadgeNum(
        context: Application,
        notification: Notification?,
        count: Int
    ): Notification? {
        TODO("Not yet implemented")
        /**
         * val iconBadgeNumModel: IconBadgeNumModel = getSetIconBadgeNumModel(context)
        return iconBadgeNumModel.setIconBadgeNum(context, notification, count)
         */
    }

}