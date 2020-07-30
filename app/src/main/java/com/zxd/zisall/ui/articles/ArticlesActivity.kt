package com.zxd.zisall.ui.articles

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.ContentBean
import io.noties.markwon.Markwon
import io.noties.markwon.image.AsyncDrawable
import io.noties.markwon.image.glide.GlideImagesPlugin
import kotlinx.android.synthetic.main.activity_articles.*

class ArticlesActivity : BaseActivity<ArticlesView, ArticlesPresent>(), ArticlesView {

    override var getLayoutId: Int = R.layout.activity_articles

    override fun initPresenter(): ArticlesPresent = ArticlesPresent()

    override fun initBind() {}

    override fun initView(savedInstanceState: Bundle) {}

    override fun initData() {
        var postId = intent.getStringExtra("_id")
        Log.d("我看看", postId)
        presenter.getArticles(postId)
    }

    override fun getArticleSuccess(contentBean: ContentBean) {
//        articles_content.text = contentBean.markdown
        val markwon = Markwon.builder(this)
            .usePlugin(GlideImagesPlugin.create(this))
            .usePlugin(GlideImagesPlugin.create(Glide.with(this)))
            .usePlugin(GlideImagesPlugin.create(object : GlideImagesPlugin.GlideStore {
                override fun cancel(target: com.bumptech.glide.request.target.Target<*>) {
                    Glide.with(this@ArticlesActivity).clear(target)
                }

                override fun load(drawable: AsyncDrawable): RequestBuilder<Drawable> {
                    return Glide.with(this@ArticlesActivity).load(drawable.destination);
                }
            }))
            .build()
        markwon.setMarkdown(articles_content, contentBean.markdown)
    }

    override fun getArticleFailure(msg: String) {
        toast(msg)
    }
}