package com.zxd.zisall.base

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.gyf.immersionbar.ImmersionBar
import com.lzy.okgo.OkGo
import com.zxd.zisall.R
import com.zxd.zisall.utils.ToastUtils

abstract class BaseFragment<V, P : BasePresent<V>> : Fragment(), BaseView {

    var presenter: P? = null
    var mImmersionBar: ImmersionBar? = null
    var mView: View? = null //缓存Fragment view

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            val contextThemeWrapper: Context =
                ContextThemeWrapper(activity, R.style.AppTheme1)
            val localInflater = inflater.cloneInContext(contextThemeWrapper)
            mView = localInflater.inflate(getContentViewLayoutID(), null)
        }
        val parent = mView!!.parent as? ViewGroup
        parent?.removeView(mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view)
        //创建presenter
        //创建presenter
        presenter = initPresenter()
        initViews(savedInstanceState)

        initEvent()
        //        初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar()
        }
    }

    override fun onResume() {
        super.onResume()
        // 在Activity中初始化P，并且连接V
        presenter!!.attach(this as V)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        //在onDestroy()生命周期中释放P中引用的V。
        presenter!!.detach()
        //在onDestroy()生命周期中取消所有子线程里面的网络连接。
        OkGo.getInstance().cancelTag(activity)
    }

    abstract fun initPresenter(): P

    abstract fun initViews(savedInstanceState: Bundle?)

    abstract fun initData()

    abstract fun initEvent()

    /**
     * 是否可以使用沉浸式
     *
     * @return the boolean
     */
    open fun isImmersionBarEnabled(): Boolean {
        return true
    }

    open fun initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.keyboardEnable(true)?.statusBarDarkFont(false, 0.2f)?.init()
    }

    abstract fun getContentViewLayoutID(): Int

    open fun <T : View?> getId(id: Int): T {
        return mView!!.findViewById<View>(id) as T
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