package com.zxd.zisall.base

import android.app.Activity
import android.media.DrmInitData
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.OnBarListener
import com.gyf.immersionbar.OnKeyboardListener
import com.gyf.immersionbar.ktx.immersionBar
import com.zxd.zisall.R
import com.zxd.zisall.utils.ToastUtils

abstract class BaseActivity<V, P : BasePresent<V>> : AppCompatActivity(), BaseView {

    lateinit var presenter: P
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        AppManager.getAppManager()?.addActivity(activity)
        if (getLayoutId != 0) {
            setContentView(getLayoutId)
            presenter = initPresenter()
            presenter.attach(activity as V)
            initBind()
            savedInstanceState?.let { initView(it) }
        }
        initBar()
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

    open fun initBar() {
        immersionBar {
            init()
        }
    }

    override fun showProgress() {
        TODO("显示进度条")
    }

    override fun hideProgress() {
        TODO("隐藏进度条")
    }

    override fun toast(s: CharSequence?) {
        s?.let { ToastUtils.showShortToast(it) }
    }

    override fun showNullLayout() {
        TODO("显示无数据布局")
    }

    override fun hideNullLayout() {
        TODO("隐藏无数据布局")
    }

    override fun showErrorLayout() {
        TODO("显示请求错误布局")
    }

    override fun hideErrorLayout() {
        TODO("隐藏请求错误布局")
    }

}
