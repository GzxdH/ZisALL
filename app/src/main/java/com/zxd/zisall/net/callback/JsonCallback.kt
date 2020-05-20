package com.zxd.zisall.net.callback

import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.model.Response
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class JsonCallback<T> : AbsCallback<T> {
    var type: Type? = null
    var clazz: Class<T>? = null

    constructor() : super()

    constructor(type: Type) : super() {
        this.type = type
    }

    constructor(clazz: Class<T>) : super() {
        this.clazz = clazz
    }

    override fun onSuccess(response: Response<T>?) {}

    override fun convertResponse(response: okhttp3.Response?): T {
        if (type == null) {
            type = if (clazz == null) {
                val genType = javaClass.genericSuperclass
                (genType as ParameterizedType?)!!.actualTypeArguments[0]
            } else {
                val convert: JsonConvert<T> = JsonConvert(clazz!!)
                return convert.convertResponse(response) as T
            }
        }

        val convert: JsonConvert<T>? = type?.let { JsonConvert(it) }
        return convert?.convertResponse(response) as T
    }
}