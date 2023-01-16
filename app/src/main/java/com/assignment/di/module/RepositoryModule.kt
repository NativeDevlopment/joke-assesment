package com.assignment.di.module



import com.assginment.domain.repository.CodingTaskRepository
import com.assginment.domain.repository.LocalJokeListRepository
import com.assignment.data.repository.CodingTaskRepositoryimpl
import org.koin.dsl.binds
import org.koin.dsl.module

val repositoryModule = module {

    single { CodingTaskRepositoryimpl(sharedPreferences =get(),api=get())
    } binds arrayOf(CodingTaskRepository::class, LocalJokeListRepository::class)



}