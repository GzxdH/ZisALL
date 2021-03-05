package com.zxd.zisall.iconbadge

import android.app.Application
import android.app.Notification


interface IconBadgeNumModel {

    @Throws(Exception::class)
    fun setIconBadgeNum(
        context: Application,
        notification: Notification?,
        count: Int
    ): Notification?

}