package com.zxd.zisall.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zxd.zisall.R
import com.zxd.zisall.base.BaseActivity

class WelcomeActivity : BaseActivity<WelcomeView,WelcomePresent>(),WelcomeView {
    override var getLayoutId: Int
        get() = TODO("Not yet implemented")
        set(value) {
            R.layout.activity_welcome
        }

    override fun initPresenter(): WelcomePresent = WelcomePresent()

    override fun initBind() {
        TODO("Not yet implemented")
    }

    override fun initView(savedInstanceState: Bundle) {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun toast(s: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun showNullLayout() {
        TODO("Not yet implemented")
    }

    override fun hideNullLayout() {
        TODO("Not yet implemented")
    }

    override fun showErrorLayout() {
        TODO("Not yet implemented")
    }

    override fun hideErrorLayout() {
        TODO("Not yet implemented")
    }

}
