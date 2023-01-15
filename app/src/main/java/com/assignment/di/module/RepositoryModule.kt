package com.assignment.di.module



import com.assginment.domain.repository.CodingTaskRepository
import com.assignment.data.repository.CodingTaskRepositoryimpl
import org.koin.dsl.module

val repositoryModule = module {

    factory  <CodingTaskRepository> {

        CodingTaskRepositoryimpl(get())
    }

}