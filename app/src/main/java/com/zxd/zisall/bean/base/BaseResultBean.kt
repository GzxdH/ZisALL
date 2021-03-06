package com.zxd.zisall.bean.base

import java.io.Serializable

class BaseResultBean<T> : Serializable {
    var status = 0
    var page = 0
    var page_count = 0
    var total_counts = 0
    var msg: String? = null
    var msg_cn: String? = null
    var data: T? = null
}