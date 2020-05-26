package com.zxd.zisall.net.callback

import com.google.gson.stream.JsonReader
import com.lzy.okgo.convert.Converter
import com.zxd.zisall.bean.base.BaseResponseBean
import com.zxd.zisall.bean.base.ResponseBean
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class JsonConvert<T> : Converter<T> {
    lateinit var type: Type
    lateinit var clazz: Class<T>

    constructor() : super()

    constructor(type: Type) : super() {
        this.type = type
    }

    constructor(clazz: Class<T>) : super() {
        this.clazz = clazz
    }

    override fun convertResponse(response: Response?): T? {
        if (type == null) {
            type = if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                val genType = javaClass.genericSuperclass
                (genType as ParameterizedType?)!!.actualTypeArguments[0]
            } else {
                return response?.let { parseClass(it, clazz) }
            }
        }

        return if (type is ParameterizedType) {
            response?.let { parseParameterizedType(it, type as ParameterizedType) }
        } else if (type is Class<*>) {
            response?.let { parseClass(it, type as Class<*>) }
        } else {
            response?.let { parseType(it, type) }
        }
    }

    @Throws(Exception::class)
    private fun parseClass(response: Response, rawType: Class<*>?): T? {
        if (rawType == null) return null
        val body = response.body() ?: return null
        val jsonReader =
            JsonReader(body.charStream())
        return if (rawType == String::class.java) {
            body.string() as T
        } else if (rawType == JSONObject::class.java) {
            JSONObject(body.string()) as T
        } else if (rawType == JSONArray::class.java) {
            JSONArray(body.string()) as T
        } else {
            val t: T = Convert.fromJson(jsonReader, rawType)
            response.close()
            t
        }
    }

    @Throws(Exception::class)
    private fun parseType(response: Response, type: Type?): T? {
        if (type == null) return null
        val body = response.body() ?: return null
        val jsonReader =
            JsonReader(body.charStream())

        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        val t: T = Convert.fromJson(jsonReader, type)
        response.close()
        return t
    }

    @Throws(Exception::class)
    private fun parseParameterizedType(
        response: Response,
        type: ParameterizedType?
    ): T? {
        if (type == null) return null
        val body = response.body() ?: return null
        val jsonReader =
            JsonReader(body.charStream())
        val rawType = type.rawType // 泛型的实际类型
        val typeArgument = type.actualTypeArguments[0] // 泛型的参数
        return if (rawType !== ResponseBean::class.java) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
            val t: T = Convert.fromJson(jsonReader, type)
            response.close()
            t
        } else {
            if (typeArgument === Void::class.java) {
                // 泛型格式如下： new JsonCallback<ResponseBean<Void>>(this)
                val baseResponseBean: BaseResponseBean =
                    Convert.fromJson(jsonReader, BaseResponseBean::class.java)
                response.close()
                baseResponseBean.toResponseBean() as T
            } else {
                // 泛型格式如下： new JsonCallback<ResponseBean<内层JavaBean>>(this)
                val responseBean = Convert.fromJson(jsonReader, type) as T
                response.close()
                responseBean as T
            }
        }
    }

}