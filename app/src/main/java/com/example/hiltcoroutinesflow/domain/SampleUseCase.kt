package com.example.hiltcoroutinesflow.domain

import com.example.hiltcoroutinesflow.data.ApiRepos
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val apiRepos: ApiRepos
) {
    suspend operator fun invoke(): JsonObject {
        return apiRepos.sampleGet()
    }
}