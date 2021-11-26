package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.model.HiltResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiRepos {
    @GET("api/user")
    suspend fun sampleGet(): Response<HiltResponse>
}