package com.zxd.zisall.ui.webToShow

import android.net.Uri
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zxd.zisall.R
import kotlinx.android.synthetic.main.activity_web_show.*

class WebShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_show)
        val url = intent.getStringExtra("url")
        toShow(url)
    }

    private fun toShow(url: String) {
        webView.getSettings().setTextZoom(100) //当前字体百分比
        webView.getSettings().setDefaultTextEncodingName("utf-8") //指定编码方式
        webView.getSettings().setJavaScriptEnabled(true) //支持js
        webView.getSettings().setDomStorageEnabled(true) //设置支持DomStorage
        webView.getSettings().setAllowFileAccess(true) //设置在WebView内部是否允许访问文件
        webView.getSettings()
            .setBlockNetworkLoads(false) //设置WebView是否从网络加载资源，Application需要设置访问网络权限，否则报异常
        webView.getSettings()
            .setBlockNetworkImage(false) //设置WebView是否以http、https方式访问从网络加载图片资源，默认false
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true)
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true)
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true)
        webView.loadUrl(url)
        // 给WebView设置监听
        webView.setWebViewClient(object : WebViewClient() {
            //跳转连接
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                // 所有连接强制在当前WeiView加载，不跳服务器
                webView.loadUrl(url)
                return true
            }

            //加载结束
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webView.loadUrl("javascript:openMusic()") //加载结束，加载音乐
            }
        })
        webView.setWebChromeClient(object : WebChromeClient() {
            /**
             * 8(Android 2.2) <= API <= 10(Android 2.3)回调此方法
             */
            private fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
                // (2)该方法回调时说明版本API < 21，此时将结果赋值给 mUploadCallbackBelow，使之 != null
//                mUploadCallbackBelow = uploadMsg
//                takePhoto()
            }

            /**
             * 11(Android 3.0) <= API <= 15(Android 4.0.3)回调此方法
             */
            fun openFileChooser(
                uploadMsg: ValueCallback<Uri>,
                acceptType: String?
            ) {
                // 这里我们就不区分input的参数了，直接用拍照
                openFileChooser(uploadMsg)
            }

            /**
             * 16(Android 4.1.2) <= API <= 20(Android 4.4W.2)回调此方法
             */
            fun openFileChooser(
                uploadMsg: ValueCallback<Uri>,
                acceptType: String?,
                capture: String?
            ) {
                // 这里我们就不区分input的参数了，直接用拍照
                openFileChooser(uploadMsg)
            }

            /**
             * API >= 21(Android 5.0.1)回调此方法
             */
            override fun onShowFileChooser(
                webView: WebView,
                valueCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                // (1)该方法回调时说明版本API >= 21，此时将结果赋值给 mUploadCallbackAboveL，使之 != null
//                mUploadCallbackAboveL = valueCallback
//                takePhoto()
                return true
            }
        })
    }

}
