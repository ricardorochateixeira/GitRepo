package com.example.gitrepo.app.framework.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class GitReposApi(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean?,
    @Json(name = "items")
    val items: List<GitRepoApiModel>?,
    @Json(name = "total_count")
    val totalCount: Int?
)

