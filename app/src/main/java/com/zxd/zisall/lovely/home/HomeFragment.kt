package com.zxd.zisall.lovely.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseFragment
import com.zxd.zisall.bean.BannerBean
import com.zxd.zisall.bean.CategoriesBean
import com.zxd.zisall.ui.categories.CategoriesActivity
import com.zxd.zisall.ui.home.HomeAdapter
import com.zxd.zisall.ui.home.HomePresent
import com.zxd.zisall.ui.home.HomeView
import com.zxd.zisall.ui.webToShow.WebShowActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeFragment : BaseFragment<HomeView, HomePresent>(), HomeView {

//    private lateinit var homeViewModel: HomeViewModel

    private var titles = arrayListOf<String>()
    private var images = arrayListOf<String>()
    private var urls = arrayListOf<String>()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        homeViewModel =
////            ViewModelProviders.of(this).get(Ho meViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
////        val textView: TextView = root.findViewById(R.id.text_home)
////        homeViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//        return root
//    }

    override fun initPresenter(): HomePresent = HomePresent()

    override fun initViews(savedInstanceState: Bundle?) {}

    override fun initEvent() {}

    override fun initData() {
        presenter?.getBannerList()
        presenter?.getCategories("Article")
    }
 
    override fun getContentViewLayoutID(): Int = R.layout.fragment_home

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
            val intent: Intent = Intent(activity, WebShowActivity::class.java)
            intent.putExtra("url", urls[position])
            startActivity(intent)
        }
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
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        recycler_home.adapter = myWalletListAdapter
        myWalletListAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        myWalletListAdapter.isAnimationFirstOnly = false
        myWalletListAdapter.setOnItemClickListener { adapter, view, position ->
            val intent: Intent = Intent(activity, CategoriesActivity::class.java)
            intent.putExtra("type", categoriesBean[position].type)
            startActivity(intent)
        }
    }

    override fun getCategoriesFailure(msg: String) {
        toast(msg)
    }

}