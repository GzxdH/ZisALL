@file:Suppress("UNUSED_ANONYMOUS_PARAMETER")

package com.zxd.zisall.ui.categories

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.SortBean
import com.zxd.zisall.bean.SortBeanItem
import com.zxd.zisall.ui.articles.ArticlesActivity
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : BaseActivity<CategoriesView, CategoriesPresent>(), CategoriesView,
    SpringView.OnFreshListener {

    var page: Int = 1
    var count: Int = 10
    var category: String = "Article"
    var type: String = ""
    var data: ArrayList<SortBeanItem> = ArrayList()

    override var getLayoutId: Int = R.layout.activity_categories

    override fun initPresenter(): CategoriesPresent = CategoriesPresent()

    override fun initBind() {
        springView_categories.setHeader(DefaultHeader(this))
        springView_categories.setFooter(DefaultFooter(this))
        springView_categories.setGive(SpringView.Give.BOTH)
        springView_categories.setType(SpringView.Type.FOLLOW)
        springView_categories.setListener(this)
    }

    override fun initView(savedInstanceState: Bundle) {}

    override fun initData() {

        type = intent.getStringExtra("type")
        if (TextUtils.isEmpty(type)) {
            finish()
            return
        }
        presenter.getSortList(category, type, page, count)

    }

    override fun getSortSuccess(sortBean: SortBean) {
        springView_categories.onFinishFreshAndLoad()
        if (page == 1) {
            data.clear()
        }
        data.addAll(sortBean)
        val myCategoriesAdapter = CategoriesAdapter(R.layout.layout_categories, data)
        recycler_categories.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recycler_categories.adapter = myCategoriesAdapter
        myCategoriesAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        myCategoriesAdapter.isAnimationFirstOnly = false
        myCategoriesAdapter.setOnItemClickListener { adapter, view, position ->
            val intent: Intent = Intent(this, ArticlesActivity::class.java)
            intent.putExtra("_id", data[position]._id)
            startActivity(intent)
        }
    }

    override fun getSortFailure(msg: String) {
        springView_categories.onFinishFreshAndLoad()
        toast(msg)
    }

    override fun onLoadmore() {
        page += 1
        presenter.getSortList(category, type, page, count)
    }

    override fun onRefresh() {
        page = 1
        presenter.getSortList(category, type, page, count)
    }
}