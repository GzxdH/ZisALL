package com.zxd.zisall.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.DBCookieStore
import com.lzy.okgo.https.HttpsUtils
import com.lzy.okgo.https.HttpsUtils.SSLParams
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level


class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initOkGo()
    }

    /*
    初始化XCrash
    Tombstone 文件默认将被写入到 Context#getFilesDir() + "/tombstones" 目录。（通常在： /data/data/PACKAGE_NAME/files/tombstones）
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        xcrash.XCrash.init(this)
    }

    private fun initOkGo() {
        /*
        默认写法
        OkGo.getInstance().init(this)
        */
        /*
        自定义写法
        val headers = HttpHeaders()
        headers.put("commonHeaderKey1", "commonHeaderValue1") //header不支持中文，不允许有特殊字符

        val params = HttpParams()
        params.put("commonParamsKey1", "commonParamsValue1") //param支持中文,直接传,不要自己编码

        .addCommonHeaders(headers) //全局公共头
        .addCommonParams(params) //全局公共参数

        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(CookieJarImpl(SPCookieStore(this)))
        //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(CookieJarImpl(MemoryCookieStore()))

        //方法一：信任所有证书,不安全有风险
        val sslParams1 = HttpsUtils.getSslSocketFactory()
        //方法二：自定义信任规则，校验服务端证书
        val sslParams2: SSLParams = HttpsUtils.getSslSocketFactory(SafeTrustManager())
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        val sslParams3 = HttpsUtils.getSslSocketFactory(assets.open("srca.cer"))
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        val sslParams4 = HttpsUtils.getSslSocketFactory(
            assets.open("xxx.bks"),
            "123456",
            assets.open("yyy.cer")
        )
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager)
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        builder.hostnameVerifier(SafeHostnameVerifier())
         */

        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO)

        builder.addInterceptor(loggingInterceptor)

        //全局的读取超时时间
        builder.readTimeout(
            20000
            , TimeUnit.MILLISECONDS
        )
        //全局的写入超时时间
        builder.writeTimeout(
            20000
            , TimeUnit.MILLISECONDS
        )
        //全局的连接超时时间
        builder.connectTimeout(
            20000
            , TimeUnit.MILLISECONDS
        )

        //使用数据库保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(CookieJarImpl(DBCookieStore(this)))

        OkGo.getInstance().init(this) //必须调用初始化
            .setOkHttpClient(builder.build()) //建议设置OkHttpClient，不设置将使用默认的
            .setCacheMode(CacheMode.NO_CACHE) //全局统一缓存模式，默认不使用缓存，可以不传
            .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount =
            0 //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

    }
}