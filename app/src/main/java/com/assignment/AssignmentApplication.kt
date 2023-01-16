package com.assignment

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.assignment.di.appcomponents.appComponent
import org.koin.core.context.startKoin

class AssignmentApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AssignmentApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appComponent)

        }
    }
}