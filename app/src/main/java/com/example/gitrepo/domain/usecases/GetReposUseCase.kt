package com.example.gitrepo.domain.usecases

import com.example.gitrepo.app.framework.network.GitReposApi
import com.example.gitrepo.data.repository.GetRepos
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import javax.inject.Inject

class GetReposUseCase
    @Inject constructor(private val getRepos: GetRepos): BaseUseCase<String, Observable<GitReposApi>> {
    override fun invoke(params: String): Observable<GitReposApi> = getRepos.getRepos(params)
}