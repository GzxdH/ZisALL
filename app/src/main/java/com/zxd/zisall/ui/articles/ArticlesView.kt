package com.zxd.zisall.ui.articles

import com.zxd.zisall.bean.ContentBean

interface ArticlesView {

    fun getArticleSuccess(contentBean: ContentBean)

    fun getArticleFailure(msg: String)

}