package com.zxd.zisall.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.BarProperties
import com.gyf.immersionbar.ktx.immersionBar
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.ui.categories.CategoriesActivity
import com.zxd.zisall.ui.webToShow.WebShowActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<HomeView, HomePresent>(), HomeView {

    private var titles = arrayListOf<String>()
    private var images = arrayListOf<String>()
    private var urls = arrayListOf<String>()
//    private val mTitles = arrayOf(
//        "热门", "iOS", "Android"
//        , "前端", "后端", "设计", "工具资源"
//    )

    override var getLayoutId: Int = R.layout.activity_home

    override fun initPresenter(): HomePresent = HomePresent()

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
                .fitCenter()
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
        presenter.getCategories("Article")
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

    override fun getCategoriesSuccess(categoriesBean: CategoriesBean) {
        val myWalletListAdapter = HomeAdapter(R.layout.layout_article, categoriesBean)
        recycler_home.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recycler_home.adapter = myWalletListAdapter
        myWalletListAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        myWalletListAdapter.isAnimationFirstOnly = false
        myWalletListAdapter.setOnItemClickListener { adapter, view, position ->
            val intent: Intent = Intent(this, CategoriesActivity::class.java)
            intent.putExtra("type", categoriesBean[position].type)
            startActivity(intent)
        }
    }

    override fun getCategoriesFailure(msg: String) {
        toast(msg)
    }

//    override fun getSortSuccess(sortBean: SortBean) {
//        Log.d("我看看", "--" + sortBean[0].author)
//        //https://www.tianqiapi.com/index/doc?version=day
//    }
//
//    override fun getSortFailure(msg: String) {
//        Log.d("我看看", "--$msg")
//    }

}
