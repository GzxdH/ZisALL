package com.zxd.zisall.ui.articles

import com.zxd.zisall.base.BasePresent
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.bean.ContentBean
import com.zxd.zisall.bean.SortBean
import com.zxd.zisall.bean.base.BaseResultBean
import com.zxd.zisall.net.HttpTo
import com.zxd.zisall.net.UrlConstant
import com.zxd.zisall.net.callback.JsonCallback
import okhttp3.Response
import java.util.*

class ArticlesPresent : BasePresent<ArticlesView>() {

    fun getArticles(postId: String) {
        val url =
            UrlConstant.ARTICLES + postId
        val map: Map<String, String> =
            HashMap()
        HttpTo.getRequest(
            url,
            this,
            map,
            object : JsonCallback<BaseResultBean<ContentBean?>?>() {
                @Throws(Throwable::class)
                override fun convertResponse(response: Response?): BaseResultBean<ContentBean?>? {
                    return super.convertResponse(response)
                }

                override fun onSuccess(response: com.lzy.okgo.model.Response<BaseResultBean<ContentBean?>?>?) {
                    super.onSuccess(response)
                    if (response?.body()?.status == 100) {
                        view?.getArticleSuccess(response.body()?.data!!)
                    } else {
                        view?.getArticleFailure("出错了+" + response?.body()?.status)
                    }
                }

                override fun onError(response: com.lzy.okgo.model.Response<BaseResultBean<ContentBean?>?>?) {
                    super.onError(response)
                }
            })
    }

}