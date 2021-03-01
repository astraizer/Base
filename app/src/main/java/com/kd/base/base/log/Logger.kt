package com.kd.base.base.log

import java.io.File

interface Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, e: Exception?)
    fun e(tag: String, e: Exception?)
    fun w(tag: String, message: String)
    fun i(tag: String, message: String)
    fun getOrCreateLogFile(): File?
}