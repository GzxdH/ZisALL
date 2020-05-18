package com.zxd.zisall.base

open class BasePresent<T> {
    var view: T? = null

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        view = null
    }
}