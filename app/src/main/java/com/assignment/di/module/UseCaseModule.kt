package com.assignment.di.module

import com.assginment.domain.usecases.GetJokeUseCase
import com.assginment.domain.usecases.GetJokeUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetJokeUseCase> {
        GetJokeUseCaseImpl(repository = get())
    }
}