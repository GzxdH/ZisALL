package com.zxd.zisall.ui.welcome

import com.zxd.zisall.bean.BannerBean

interface WelcomeView {

    fun getBannerSuccess(bannerBean: BannerBean)

    fun getBannersFailure(msg: String)

}