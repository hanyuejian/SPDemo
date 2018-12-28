package com.hanyue.fastsave

import android.app.Application

open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        var instance: BaseApp? = null
    }

}