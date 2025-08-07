package org.tumo.MyTumo

import android.app.Application
import android.content.Context
import org.tumo.MyTumo.service.login.TokenHolder

class MainApplication : Application() {
    object context {
        lateinit var application:Context
    }
    override fun onCreate() {
        super.onCreate()
        context.application = this
        TokenHolder.initialize(this)
    }
}