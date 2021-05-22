package com.batdemir.template.di.module

import com.batdemir.template.data.local.dao.GithubDao
import com.batdemir.template.data.local.dao.StackOverFlowDao
import com.batdemir.template.data.mediator.GithubMediator
import com.batdemir.template.data.mediator.StackOverFlowMediator
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.data.remote.service.StackOverFlowService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MediatorModule {
    @Singleton
    @Provides
    fun provideMediatorGithub(
        dao: GithubDao,
        service: GithubService
    ) = GithubMediator(dao, service)

    @Singleton
    @Provides
    fun provideMediatorStackOverFlow(
        dao: StackOverFlowDao,
        service: StackOverFlowService
    ) = StackOverFlowMediator(dao, service)
}