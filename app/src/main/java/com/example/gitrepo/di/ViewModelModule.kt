package com.example.gitrepo.di

import com.example.gitrepo.data.repository.GetRepos
import com.example.gitrepo.domain.usecases.GetReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    fun provideGetReposUseCase(getRepos: GetRepos): GetReposUseCase =
        GetReposUseCase(getRepos)
}