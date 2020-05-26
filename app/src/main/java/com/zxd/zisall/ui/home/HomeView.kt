package com.zxd.zisall.ui.home

import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.bean.CategoriesItem
import com.zxd.zisall.bean.SortBean

interface HomeView {

    fun getBannerSuccess(bannerBean: BannerBean)

    fun getBannersFailure(msg: String)

//    fun getSortSuccess(sortBean: SortBean)
//
//    fun getSortFailure(msg: String)

    fun getCategoriesSuccess(categoriesBean : CategoriesBean)

    fun getCategoriesFailure(msg: String)

}