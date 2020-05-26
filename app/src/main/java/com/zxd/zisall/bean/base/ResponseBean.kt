package com.zxd.zisall.bean.base

import java.io.Serializable

class ResponseBean<T> : Serializable {
    var code = 0
    var message: String? = null
    var status = 0
    var page = 0
    var page_count = 0
    var total_counts = 0
    var data: T? = null
}