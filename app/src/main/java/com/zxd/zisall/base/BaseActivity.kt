package com.zxd.zisall.base

import android.app.Activity
import android.media.DrmInitData
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V, P : BasePresent<V>> : AppCompatActivity(), BaseView {

    protected lateinit var presenter: P
    protected lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        AppManager.getAppManager()?.addActivity(activity)
        if (getLayoutId != 0) {
            setContentView(getLayoutId)
            presenter = initPresenter()
            presenter.attach(activity as V)
            initBind()
            initView(savedInstanceState!!)
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager()?.finishActivity(activity)
        presenter.detach()
    }

    abstract var getLayoutId: Int

    abstract fun initPresenter(): P

    abstract fun initBind()

    abstract fun initView(savedInstanceState: Bundle)

    abstract fun initData()

}
