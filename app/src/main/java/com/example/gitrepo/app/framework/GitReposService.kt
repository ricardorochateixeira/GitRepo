package com.example.gitrepo.app.framework

import com.example.gitrepo.app.framework.network.GitReposApi
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitReposService {

    @GET("search/repositories?sort=stars")
    fun getRepos(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") itemsPerPage: Int = 100
    ): Observable<GitReposApi>
}