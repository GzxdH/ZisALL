package com.zxd.zisall.bean

class BannerBean : ArrayList<BannerBeanItem>()

data class BannerBeanItem(
    val image: String,
    val title: String,
    val url: String
)