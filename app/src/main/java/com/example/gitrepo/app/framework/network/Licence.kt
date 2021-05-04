package com.example.gitrepo.app.framework.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class License(
    @Json(name = "key")
    val key: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "node_id")
    val nodeId: String?,
    @Json(name = "spdx_id")
    val spdxId: String?,
    @Json(name = "url")
    val url: String?
)