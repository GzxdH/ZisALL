package com.zxd.zisall.ui.home

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxd.zisall.R
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.bean.CategoriesItem

class HomeAdapter(layoutResId: Int, data: ArrayList<CategoriesItem>) :
    BaseQuickAdapter<CategoriesItem, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: CategoriesItem) {
        val img: ImageView = holder.getView(R.id.image_article)
        Glide
            .with(context)
            .load(item.coverImageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .centerCrop()
            .dontAnimate()
            .into(img)
        holder.setText(R.id.name_article, item.title)
        holder.setText(R.id.content_article, item.desc)
    }

}