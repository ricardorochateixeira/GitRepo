package com.example.gitrepo.di

import com.example.gitrepo.app.framework.repository.GetReposImpl
import com.example.gitrepo.data.repository.GetRepos
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindGetRepos(getRepos: GetReposImpl): GetRepos
}