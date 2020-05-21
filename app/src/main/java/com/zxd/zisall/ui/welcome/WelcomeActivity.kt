package com.zxd.zisall.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.view.get
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.ui.webToShow.WebShowActivity
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : BaseActivity<WelcomeView, WelcomePresent>(), WelcomeView {

    private var titles = arrayListOf<String>()
    private var images = arrayListOf<String>()
    private var urls = arrayListOf<String>()

    override var getLayoutId: Int = R.layout.activity_welcome

    override fun initPresenter(): WelcomePresent = WelcomePresent()

    override fun initBind() {}

    override fun initView(savedInstanceState: Bundle) {
    }

    private fun useBanner() {
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
            val intent: Intent = Intent(this, WebShowActivity::class.java)
            intent.putExtra("url", urls[position])
            startActivity(intent)
        }
    }

    override fun initData() {
        presenter.getBannerList()
    }

    override fun getBannerSuccess(bannerBean: BannerBean) {
        titles.clear()
        images.clear()
        urls.clear()
        repeat(bannerBean.size) {
            titles.add(bannerBean[it].title)
            images.add(bannerBean[it].image)
            urls.add(bannerBean[it].url)
//            HttpTo.downFile(
//                bannerBean[it].image,
//                this,
//                object : FileCallback("" + System.currentTimeMillis() + ".jpg") {
//                    override fun onStart(request: Request<File?, out Request<*, *>?>?) {
//                        Log.d("我看看", "--开始下载")
//                    }
//
//                    override fun onSuccess(response: Response<File>) {
//                        Log.d("我看看", "--下载成功--")
//                    }
//
//                    override fun onError(response: Response<File>) {
//                        Log.d("我看看", "--下载失败")
//                    }
//                })
        }
        useBanner()
    }

    override fun getBannersFailure(msg: String) {
        toast(msg)
    }
}
