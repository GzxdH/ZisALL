package com.zxd.zisall.ui.categories

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zxd.zisall.R
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.bean.CategoriesItem
import com.zxd.zisall.bean.SortBean
import com.zxd.zisall.bean.SortBeanItem

class CategoriesAdapter(layoutResId: Int, data: ArrayList<SortBeanItem>) :
    BaseQuickAdapter<SortBeanItem, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: SortBeanItem) {
        val img: ImageView = holder.getView(R.id.image_categories)
        Glide
            .with(context)
            .load(item.images[0])
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .centerCrop()
            .dontAnimate()
            .into(img)
        holder.setText(R.id.name_categories, item.title)
        holder.setText(R.id.content_categories, item.desc)
        holder.setText(R.id.time_categories, item.createdAt)
        holder.setText(R.id.author_categories, item.author)
    }

}