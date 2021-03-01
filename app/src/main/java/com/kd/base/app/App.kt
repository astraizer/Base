package com.kd.base.app

import android.app.Application
import com.kd.base.base.log.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject lateinit var logger: Logger
}