package com.assignment.di.module

import android.content.Context
import com.assignment.AssignmentApplication
import com.assignment.util.SharedPrefConstants
import com.assignment.util.SharedPrefHelper

import org.koin.dsl.module


val sharedPrefModule = module {
    single {
        AssignmentApplication.applicationContext()
            .getSharedPreferences(SharedPrefConstants.APP_PREFS, Context.MODE_PRIVATE)
    }

    factory {
        SharedPrefHelper()
    }
}
