package com.kd.base.base.log

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

//    @Provides
//    @Singleton
//    fun provideTimberTree(): Timber.Tree =
//        object : Timber.DebugTree() {
//            override fun isLoggable(tag: String?, priority: Int) = true
//        }

    @Provides
    @Singleton
    fun provideLogUtility(
        @ApplicationContext context: Context
    ): Logger {
        return LoggerDefault(context)
    }

}