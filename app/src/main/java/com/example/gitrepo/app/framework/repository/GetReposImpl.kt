package com.example.gitrepo.app.framework.repository

import com.example.gitrepo.app.framework.GitReposService
import com.example.gitrepo.app.framework.network.GitReposApi
import com.example.gitrepo.data.repository.GetRepos
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetReposImpl @Inject constructor(
    private val gitReposService: GitReposService
): GetRepos {
    override fun getRepos(query: String): Observable<GitReposApi> = gitReposService.getRepos(query)
}