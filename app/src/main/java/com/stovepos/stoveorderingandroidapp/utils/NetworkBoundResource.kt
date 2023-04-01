package com.stovepos.stoveorderingandroidapp.utils

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (e: Exception) {
            query().map { Resource.Error(e.toString(), it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}