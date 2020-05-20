package com.zxd.zisall.welcome

import com.zxd.zisall.bean.BannerBean

interface WelcomeView {

    fun getBannerSuccess(bannerBean: BannerBean)

    fun getBannersFailure(msg: String)

}