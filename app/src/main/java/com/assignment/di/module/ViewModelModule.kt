package com.assignment.di.module

import com.assignment.presentaion.viewmodel.JokesViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
    JokesViewModel(get())

    }

}