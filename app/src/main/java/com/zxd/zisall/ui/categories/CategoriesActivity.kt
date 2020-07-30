@file:Suppress("UNUSED_ANONYMOUS_PARAMETER")

package com.zxd.zisall.ui.categories

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity
import com.zxd.zisall.bean.SortBean
import com.zxd.zisall.ui.articles.ArticlesActivity
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : BaseActivity<CategoriesView,CategoriesPresent>(),CategoriesView {

    val page:Int = 1
    val count:Int = 10

    override var getLayoutId: Int = R.layout.activity_categories

    override fun initPresenter(): CategoriesPresent = CategoriesPresent()

    override fun initBind() {}

    override fun initView(savedInstanceState: Bundle) {}

    override fun initData() {

        /**
        category 可接受参数 All(所有分类) | Article | GanHuo | Girl
        type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
        count: [10, 50]
        page: >=1

         */

        var type = intent.getStringExtra("type")
        if(TextUtils.isEmpty(type)){
            finish()
            return
        }
        presenter.getSortList("Article", type, page, count)

    }

    override fun getSortSuccess(sortBean: SortBean) {
        val myCategoriesAdapter = CategoriesAdapter(R.layout.layout_categories, sortBean)
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
            intent.putExtra("_id", sortBean[position]._id)
            Log.d("我看看",sortBean[position]._id)
            startActivity(intent)
        }
    }

    override fun getSortFailure(msg: String) {
        toast(msg)
    }
}