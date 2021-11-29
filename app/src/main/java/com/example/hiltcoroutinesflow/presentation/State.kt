package com.example.hiltcoroutinesflow.presentation

sealed class State <out T>{
    data class LoadingState(val isLoading: Boolean) : State<Nothing>()
    data class ErrorState(var exception:Throwable):State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
    object Empty : State<Nothing>()
}