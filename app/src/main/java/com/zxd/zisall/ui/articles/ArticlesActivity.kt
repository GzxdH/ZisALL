package com.zxd.zisall.ui.articles

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.ContentBean
import io.noties.markwon.Markwon
import io.noties.markwon.image.AsyncDrawable
import io.noties.markwon.image.glide.GlideImagesPlugin
import kotlinx.android.synthetic.main.activity_articles.*
import org.jsoup.Jsoup

class ArticlesActivity : BaseActivity<ArticlesView, ArticlesPresent>(), ArticlesView {

    override var getLayoutId: Int = R.layout.activity_articles

    override fun initPresenter(): ArticlesPresent = ArticlesPresent()

    override fun initBind() {}

    override fun initView(savedInstanceState: Bundle) {}

    override fun initData() {
        var postId = intent.getStringExtra("_id")
        presenter.getArticles(postId)
    }

    override fun getArticleSuccess(contentBean: ContentBean) {
        if (contentBean.markdown != null) {
            articles_content.visibility = View.VISIBLE
            wv_articles_content.visibility = View.GONE
            val markWon = Markwon.builder(this)
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
            markWon.setMarkdown(articles_content, contentBean.markdown)
        } else {
            articles_content.visibility = View.GONE
            wv_articles_content.visibility = View.VISIBLE

            //解析并展示html文本，并设置图片大小，以防超出
            val doc = Jsoup.parse(contentBean.content)
            val elements = doc.getElementsByTag("img")
            for (element in elements) {
                element.attr("width", "100%").attr("height", "auto")
            }
            val htmlText = doc.toString()
            wv_articles_content.loadDataWithBaseURL(null, htmlText, "text/html", "utf-8", null)
        }
    }

    override fun getArticleFailure(msg: String) {
        toast(msg)
    }
}