package com.example.gitrepo.data.repository

import com.example.gitrepo.app.framework.network.GitReposApi
import io.reactivex.Observable
import io.reactivex.Single

interface GetRepos {
    fun getRepos(query: String): Observable<GitReposApi>
}