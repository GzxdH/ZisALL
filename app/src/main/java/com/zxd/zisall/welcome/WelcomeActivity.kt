package com.zxd.zisall.welcome

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.base.BaseResultBean
import com.zxd.zisall.net.HttpTo
import com.zxd.zisall.net.UrlConstant
import com.zxd.zisall.net.callback.JsonCallback
import com.zxd.zisall.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_welcome.*
import java.io.File
import java.util.HashMap


class WelcomeActivity : BaseActivity<WelcomeView, WelcomePresent>(), WelcomeView {

    private var titles = arrayListOf<String>()
    private var images = arrayListOf<String>()

    override var getLayoutId: Int = R.layout.activity_welcome

    override fun initPresenter(): WelcomePresent = WelcomePresent()

    override fun initBind() {}

    override fun initView(savedInstanceState: Bundle) {
    }

    private fun useBanner() {
        banner_guide_content.setAutoPlayAble(true)
        banner_guide_content.setAdapter(BGABanner.Adapter<ImageView, String> { banner, itemView, model, position ->
            Glide.with(this)
                .load(model)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .dontAnimate()
                .into(itemView)
        })
        banner_guide_content.setData(images, titles)
        banner_guide_content.setDelegate { banner, itemView, model, position ->
            Log.d("我看看", "点击了第" + (position + 1) + "页")
        }
    }

    override fun initData() {
        presenter.getBannerList()
    }

    override fun getBannerSuccess(bannerBean: BannerBean) {
        repeat(bannerBean.size) {
            titles.add(bannerBean[it].title)
            images.add(bannerBean[it].image)
            HttpTo.downFile(
                bannerBean[it].image,
                this,
                object : FileCallback("" + System.currentTimeMillis() + ".jpg") {
                    override fun onStart(request: Request<File?, out Request<*, *>?>?) {
                        Log.d("我看看", "--开始下载")
                    }

                    override fun onSuccess(response: Response<File>) {
                        Log.d("我看看", "--下载成功--")
                    }
//Glide: Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored
                    override fun onError(response: Response<File>) {
                        Log.d("我看看", "--下载失败")
                    }
                })

        }
        useBanner()
    }

    override fun getBannersFailure(msg: String) {
        toast(msg)
    }
}
