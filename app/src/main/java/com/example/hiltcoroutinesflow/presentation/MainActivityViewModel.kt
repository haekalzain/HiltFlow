package com.example.hiltcoroutinesflow.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hiltcoroutinesflow.domain.BiometricUsecase
import com.example.hiltcoroutinesflow.model.request.BiometricRegistRequest
import com.example.hiltcoroutinesflow.model.request.BiometricVerifyRequest
import com.example.hiltcoroutinesflow.utils.CorelationBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val biometricUsecase: BiometricUsecase) :
    ViewModel() {
    fun submitRegistBiometric(
        cifId: Int,
        cifCode: String,
        cifName: String,
        coCode: String,
        image64: String
    ) =
        biometricUsecase.submitRegistBiometric(
            BiometricRegistRequest(
                cifId,
                cifCode,
                cifName,
                "terra",
                coCode,
                "2",
                image64
            )
        ).asLiveData(viewModelScope.coroutineContext)

    fun submitVerifyBiometric(
        cifId: String,
        image64: String
    ) =
        biometricUsecase.submitVerifyBiometric(
            CorelationBuilder.newId(),
            cifId,
            BiometricVerifyRequest(
                image64
            )
        ).asLiveData(viewModelScope.coroutineContext)
}