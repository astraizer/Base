package com.kd.base.base.log

import javax.inject.Inject

abstract class BaseLogger (private val className: String) {

    @Inject
    lateinit var logger: Logger

    fun logDebug(message: String) {
        logger.d(className, message)
    }

    fun logInfo(message: String) {
        logger.i(className, message)
    }

    fun logException(message: String, exception: Exception?) {
        logger.e(className, message, exception)
    }

}