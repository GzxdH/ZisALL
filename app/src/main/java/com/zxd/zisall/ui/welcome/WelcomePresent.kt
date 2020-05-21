package com.zxd.zisall.ui.welcome

import com.zxd.zisall.base.BasePresent
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.base.BaseResultBean
import com.zxd.zisall.net.HttpTo
import com.zxd.zisall.net.UrlConstant
import com.zxd.zisall.net.callback.JsonCallback
import okhttp3.Response
import java.util.*

class WelcomePresent : BasePresent<WelcomeView>() {
    fun getBannerList() {
        val map: Map<String, String> =
            HashMap()
        HttpTo.getRequest(
            UrlConstant.BANNER,
            this,
            map,
            object : JsonCallback<BaseResultBean<BannerBean?>?>() {
                @Throws(Throwable::class)
                override fun convertResponse(response: Response?): BaseResultBean<BannerBean?>? {
                    return super.convertResponse(response)
                }

                override fun onSuccess(response: com.lzy.okgo.model.Response<BaseResultBean<BannerBean?>?>?) {
                    super.onSuccess(response)
                    if (response?.body()?.status == 100) {
                        view?.getBannerSuccess(response.body()?.data!!)
                    } else {
                        view?.getBannersFailure("出错了+" + response?.body()?.status)
                    }
                }

                override fun onError(response: com.lzy.okgo.model.Response<BaseResultBean<BannerBean?>?>?) {
                    super.onError(response)
                }
            })
    }
}