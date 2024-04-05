package com.example.testapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.UUID
import javax.inject.Inject

interface ErrorRepository {
    val errorFlow: Flow<Error>

    suspend fun sendError(error: Error)
}

sealed class Error(
    val message: String,
    /** id of the error, useful when we want to reply the same message */
    internal val id: UUID = UUID.randomUUID()
) {
    data class CatFactsRefreshError(val msg: String): Error(msg)
    data object NotConnectedToInternet: Error("Not connected to Internet")
}

class DefaultErrorRepository @Inject constructor(): ErrorRepository {

    private val localErrorFlow = MutableSharedFlow<Error>()
    override val errorFlow: Flow<Error> = localErrorFlow.asSharedFlow()
    override suspend fun sendError(error: Error) {
        localErrorFlow.emit(error)
    }

}