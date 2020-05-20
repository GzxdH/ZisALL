package com.zxd.zisall.bean

import java.io.Serializable

class ResponseBean<T> : Serializable {
    var code = 0
    var message: String? = null
    var data: T? = null
}