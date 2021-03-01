package com.kd.base.base.viewmodel

import androidx.lifecycle.ViewModel
import com.kd.base.base.log.Logger

abstract class BaseViewModel(
    private val TAG: String,
    private val logger: Logger
): ViewModel() {

    protected fun logDebug(message: String) {
        logger.d(TAG, message)
    }

    protected fun logInfo(message: String) {
        logger.i(TAG, message)
    }

    protected fun logException(message: String, exception: Exception?) {
        logger.e(TAG, message, exception)
    }

}