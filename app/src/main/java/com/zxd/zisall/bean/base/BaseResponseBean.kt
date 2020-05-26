package com.zxd.zisall.bean.base

import java.io.Serializable

class BaseResponseBean : Serializable {
    private val serialVersionUID = -1477609349345966116L

    private var code = 0
    private var message: String? = null
    lateinit var responseBean: ResponseBean<*>

    fun toResponseBean(): ResponseBean<*> {
        val responseBean =
            responseBean as? ResponseBean<*> ?: throw Exception("还是错了")
        responseBean.code = code
        responseBean.message = message
        return responseBean
    }

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getMessage(): String? {
        return if (message == null) "" else message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
}