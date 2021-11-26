package com.example.hiltcoroutinesflow.data

import com.example.hiltcoroutinesflow.presentation.State
import com.example.hiltcoroutinesflow.utils.Utility
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRemoteDataSource {
    protected fun <T : Any> getFlowResult(call: suspend () -> Response<T>): Flow<State<T>> = flow {
        emit(State.LoadingState)
        delay(3000)
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    emit(State.DataState(body))
                } ?: emit(State.Empty)
            } else {
                emit(State.ErrorState(Exception("Response Failed")))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(State.ErrorState(Utility.resolveError(e)))
        }
    }
}