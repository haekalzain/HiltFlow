package com.example.hiltcoroutinesflow.domain

import com.example.hiltcoroutinesflow.model.HiltResponse
import com.example.hiltcoroutinesflow.presentation.State
import kotlinx.coroutines.flow.Flow

interface SecondSampleUsecase {
    fun submitApproval(): Flow<State<HiltResponse>>
}