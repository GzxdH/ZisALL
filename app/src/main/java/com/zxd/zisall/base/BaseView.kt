package com.zxd.zisall.base

interface BaseView {
    /**
     * 显示loading框
     */
    fun showProgress()

    /**
     * 隐藏loading框
     */
    fun hideProgress()

    /**
     * 设置吐司提示
     */
    fun toast(s: CharSequence?)

    /**
     * 显示空数据布局
     */
    fun showNullLayout()

    /**
     * 隐藏空数据布局
     */
    fun hideNullLayout()

    /**
     * 显示异常布局
     */
    fun showErrorLayout()

    /**
     * 隐藏异常布局
     */
    fun hideErrorLayout()
}