package com.zxd.zisall.ui.categories

import com.zxd.zisall.base.BasePresent
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.bean.SortBean
import com.zxd.zisall.bean.base.BaseResultBean
import com.zxd.zisall.net.HttpTo
import com.zxd.zisall.net.UrlConstant
import com.zxd.zisall.net.callback.JsonCallback
import okhttp3.Response
import java.util.*

class CategoriesPresent : BasePresent<CategoriesView>() {

    fun getSortList(category: String, type: String, page: Int, count: Int) {
        val url =
            UrlConstant.SORTS + "category/" + category + "/type/" + type + "/page/" + page + "/count/" + count
        val map: Map<String, String> =
            HashMap()
        HttpTo.getRequest(
            url,
            this,
            map,
            object : JsonCallback<BaseResultBean<SortBean?>?>() {
                @Throws(Throwable::class)
                override fun convertResponse(response: Response?): BaseResultBean<SortBean?>? {
                    return super.convertResponse(response)
                }

                override fun onSuccess(response: com.lzy.okgo.model.Response<BaseResultBean<SortBean?>?>?) {
                    super.onSuccess(response)
                    if (response?.body()?.status == 100) {
                        view?.getSortSuccess(response.body()?.data!!)
                    } else {
                        view?.getSortFailure("出错了+" + response?.body()?.status)
                    }
                }

                override fun onError(response: com.lzy.okgo.model.Response<BaseResultBean<SortBean?>?>?) {
                    super.onError(response)
                }
            })
    }

}