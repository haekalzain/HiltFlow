package com.example.hiltcoroutinesflow.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hiltcoroutinesflow.domain.SecondSampleUsecase
import com.example.hiltcoroutinesflow.utils.Utility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val sampleUseCase: SecondSampleUsecase) :
    ViewModel() {
fun getSampleResponse() = sampleUseCase.submitApproval().asLiveData(viewModelScope.coroutineContext)
}