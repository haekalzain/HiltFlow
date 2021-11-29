package com.example.hiltcoroutinesflow.domain

import com.example.hiltcoroutinesflow.model.request.BiometricRegistRequest
import com.example.hiltcoroutinesflow.model.request.BiometricVerifyRequest
import com.example.hiltcoroutinesflow.model.response.BiometricRegistResponse
import com.example.hiltcoroutinesflow.presentation.State
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

interface BiometricUsecase {
    fun submitRegistBiometric(bodyRequest: BiometricRegistRequest): Flow<State<BiometricRegistResponse>>

    fun submitVerifyBiometric(
        correlationId: String,
        cifId: String,
        bodyRequest: BiometricVerifyRequest
    ): Flow<State<JsonObject>>
}