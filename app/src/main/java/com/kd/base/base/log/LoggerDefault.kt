package com.kd.base.base.log

import android.content.Context
import android.os.Build
import com.kd.base.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LoggerDefault @Inject constructor(
    private val context: Context
) : Logger{

    companion object {
        const val ZIPPED_LOG_FILE_NAME = "log.zip"
        const val LOG_FILE_NAME = "log.txt"
        const val LOG_FORMAT = "%s %s - %s %s %s %s"  // date time application_version brand/model/API-ver tag message exception(if available) stacktrace(if available)
        const val TIME_THRESHOLD_IN_DAYS = 2  // time in days threshold before clear the data
    }

    /**
     * Log with debug level
     */
    override fun d(tag: String, message: String) {
        Timber.d("$tag $message")
        log("D/$tag", message)
    }

    /**
     * Log with error level
     */
    override fun e(tag: String, message: String, e: Exception?) {
        Timber.e(e,"$tag $message")
        log("E/$tag", message, e)
    }

    /**
     * Log with error level
     */
    override fun e(tag: String, e: Exception?) {
        Timber.e("$tag ${e?.localizedMessage}")
        log("E/$tag", "", e)
    }

    /**
     * Log with error level
     */
    override fun w(tag: String, message: String) {
        Timber.w("$tag $message")
        log("W/$tag", message)
    }

    /**
     * Log with error level
     */
    override fun i(tag: String, message: String) {
        Timber.i("$tag $message")
        log("I/$tag", message)
    }

    /**
     * Get log file
     */
    override fun getOrCreateLogFile(): File? {
        return try {
            val logFile = File(
                context.filesDir,
                LOG_FILE_NAME
            )
            if(!logFile.exists()) {
                // if it does not exist, then create
                logFile.createNewFile()
                // write creation date
                logFile.writeText(initiateLog())
            }

            logFile
        } catch (e: IOException) {
            null
        }
    }


    private fun log(tag: String, message: String, ex: Exception? = null) {
        runBlocking {
            launch(Dispatchers.IO) {
                appendLog(tag, message, ex)
            }
        }
    }

    // append log
    private fun appendLog(tag: String, message: String, ex: Exception? = null) {
        try {
            val logFile = getOrCreateLogFile()
            logFile?.also { file ->
                // clear data if log is more than threshold
                val creationDate = try {
                    file.readLines().firstOrNull()?.toLong() ?: System.currentTimeMillis()
                } catch (e: NumberFormatException) {
                    System.currentTimeMillis()
                }
                val expirationDate = creationDate + (86400000 * TIME_THRESHOLD_IN_DAYS)
                if (System.currentTimeMillis() > expirationDate) {
                    // write creation date
                    logFile.writeText(initiateLog())
                }

                formatLog(tag, message, ex)?.also {
                    file.appendText("$it\n")
                }
            }
        } catch (e: Exception) {
            /* nop */
        }
    }

    private fun initiateLog(): String {
        return "${System.currentTimeMillis()}\n " +
                "Version Name = ${BuildConfig.VERSION_NAME}\n" +
                "Brand Name = ${Build.BRAND}\n" +
                "Brand Model =  ${Build.MODEL}\n" +
                "Android SDK Version = ${Build.VERSION.SDK_INT}\n"
    }

    private fun formatLog(tag: String, message: String, ex: Exception? = null): String? {
        return try {
            val currentDate = Date()
            val currentDateFormatted = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(currentDate)
            val currentTimeFormatted = SimpleDateFormat("HH:mm:ss.SSS", Locale.ENGLISH).format(currentDate)
            val exceptionMessage = ex?.localizedMessage.orEmpty()
            val stackTrace = ex?.let { exception ->
                try {
                    val writer = StringWriter()
                    exception.printStackTrace(PrintWriter(writer))
                    writer.also {
                        it.close()
                    }.let {
                        "\n$it".trimEnd()
                    }
                } catch (e: Exception) {
                    null
                }
            }.orEmpty()
            String.format(LOG_FORMAT, currentDateFormatted, currentTimeFormatted, tag, message, exceptionMessage, stackTrace)
        } catch (e: Exception) {
            null
        }
    }

}