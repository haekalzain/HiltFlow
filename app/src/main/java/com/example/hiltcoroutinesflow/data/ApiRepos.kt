package com.example.hiltcoroutinesflow.data

import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiRepos {
    @GET("api/users")
    suspend fun sampleGet(): JsonObject
}