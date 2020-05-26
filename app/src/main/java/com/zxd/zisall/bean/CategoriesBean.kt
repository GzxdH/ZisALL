package com.zxd.zisall.bean

class CategoriesBean : ArrayList<CategoriesItem>()

data class CategoriesItem(
    val _id: String,
    val coverImageUrl: String,
    val desc: String,
    val title: String,
    val type: String
)