package com.zxd.zisall.net

import android.text.format.Formatter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.zxd.zisall.net.callback.JsonCallback
import com.zxd.zisall.utils.ToastUtils
import java.io.File

class HttpTo {
    companion object {
        fun <T> getRequest(
            url: String?,
            tag: Any?,
            map: Map<String, String>,
            callback: JsonCallback<T>?
        ) {
            OkGo.get<T>(url)
                .tag(tag)
                .params(map)
                .execute(callback)
        }

        fun <T> postRequest(
            url: String?,
            tag: Any?,
            map: Map<String, String>,
            callback: JsonCallback<T>?
        ) {
            OkGo.post<T>(url)
                .tag(tag)
                .params(map)
                .execute(callback)
        }

        fun downFile(
            url: String,
            tag: Any,
            callback: FileCallback
        ) {
            OkGo.get<java.io.File?>(url)
                .tag(tag)
                .headers("Content-Type", "image/png")
                .execute(callback)
        }

    }
}