package com.zxd.zisall.base

import androidx.multidex.MultiDexApplication

class App : MultiDexApplication() {

    companion object App {

        var instance: App? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = App
    }
}