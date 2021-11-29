package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.domain.BiometricUsecase
import com.example.hiltcoroutinesflow.model.request.BiometricRegistRequest
import com.example.hiltcoroutinesflow.model.request.BiometricVerifyRequest
import com.example.hiltcoroutinesflow.model.response.BiometricRegistResponse
import com.example.hiltcoroutinesflow.presentation.State
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val services: ApiRepos
) : BaseRemoteDataSource(), BiometricUsecase {
    override fun submitRegistBiometric(bodyRequest: BiometricRegistRequest): Flow<State<BiometricRegistResponse>> =
        getFlowResult { services.registBiometric(bodyRequest) }

    override fun submitVerifyBiometric(
        corelationId: String,
        personId: String,
        bodyRequest: BiometricVerifyRequest
    ): Flow<State<JsonObject>> =
        getFlowResult { services.verifyBiometric(corelationId, personId, bodyRequest) }

}