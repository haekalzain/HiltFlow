package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.presentation.State
import com.example.hiltcoroutinesflow.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRemoteDataSource {
    protected fun <T : Any> getFlowResult(call: suspend () -> Response<T>): Flow<State<T>> = flow {
        try {
            emit(State.LoadingState(true))
            val response = call()
            if (response.isSuccessful) {
                emit(State.LoadingState(false))
                val body = response.body()
                body?.let {
                    emit(State.DataState(body))
                } ?: emit(State.Empty)
            } else {
                emit(State.LoadingState(false))
                emit(State.ErrorState(Exception("Response Failed")))
            }
        } catch (e: Exception) {
            emit(State.LoadingState(false))
            e.printStackTrace()
            emit(State.ErrorState(Utility.resolveError(e)))
        }
    }
}