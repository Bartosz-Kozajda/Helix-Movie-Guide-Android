package com.androidmess.helix.common.debug

import com.androidmess.helix.common.app.AppConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class TimberL(private val appConfig: AppConfig) : L {

    override fun init() {
        if (appConfig.isDebug) {
            Timber.plant(DebugTree())
        }
    }
}

fun Any.v(message: String) {
    Timber.v(message)
}

fun Any.d(message: String) {
    Timber.d(message)
}

fun Any.w(message: String) {
    Timber.w(message)
}

fun Any.e(message: String) {
    Timber.e(message)
}

fun Any.e(throwable: Throwable) {
    Timber.e(throwable)
}