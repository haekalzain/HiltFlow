package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.model.request.BiometricRegistRequest
import com.example.hiltcoroutinesflow.model.request.BiometricVerifyRequest
import com.example.hiltcoroutinesflow.model.response.BiometricRegistResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRepos {
    @POST("biometric")
    suspend fun registBiometric(@Body bodyRequest: BiometricRegistRequest): Response<BiometricRegistResponse>

    @POST("biometric/verify/{cifId}")
    suspend fun verifyBiometric(
        @Header("X-Correlation-Id") correlationId: String,
        @Path("cifId") cifId: String,
        @Body bodyRequest: BiometricVerifyRequest
    ): Response<JsonObject>
}