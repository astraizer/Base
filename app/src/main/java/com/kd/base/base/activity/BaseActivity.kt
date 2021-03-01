package com.kd.base.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kd.base.base.log.Logger
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(){
    @Inject lateinit var logger: Logger

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInfo("onCreate()")
    }

    override fun onStart() {
        super.onStart()
        logInfo("onStart()")
    }

    override fun onResume() {
        super.onResume()
        logInfo("onResume()")
    }

    override fun onPause() {
        logInfo("onPause()")
        super.onPause()
    }

    override fun onStop() {
        logInfo("onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        logInfo("onDestroy()")
        super.onDestroy()
    }

    protected fun logDebug(message: String) {
        logger.d(localClassName, message)
    }

    protected fun logInfo(message: String) {
        logger.i(localClassName, message)
    }

    protected fun logException(message: String, exception: Exception?) {
        logger.e(localClassName, message, exception)
    }

}