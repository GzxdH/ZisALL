package com.zxd.zisall.ui.categories

import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.bean.CategoriesItem
import com.zxd.zisall.bean.SortBean

interface CategoriesView {

    fun getSortSuccess(sortBean: SortBean)

    fun getSortFailure(msg: String)

}