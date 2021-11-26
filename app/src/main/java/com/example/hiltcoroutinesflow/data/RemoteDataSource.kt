package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.domain.SecondSampleUsecase
import com.example.hiltcoroutinesflow.model.HiltResponse
import com.example.hiltcoroutinesflow.presentation.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val services: ApiRepos
) : BaseRemoteDataSource(), SecondSampleUsecase {
    override fun submitApproval(): Flow<State<HiltResponse>> =
        getFlowResult { services.sampleGet() }

}