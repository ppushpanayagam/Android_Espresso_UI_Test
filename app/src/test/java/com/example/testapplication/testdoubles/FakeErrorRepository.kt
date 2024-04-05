package com.example.testapplication.testdoubles

import com.example.testapplication.data.Error
import com.example.testapplication.data.ErrorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class FakeErrorRepository @Inject constructor(): ErrorRepository {
    private val localErrorFlow = MutableSharedFlow<Error>()
    override val errorFlow: Flow<Error> = localErrorFlow.asSharedFlow()

    override suspend fun sendError(error: Error) {
        localErrorFlow.emit(error)
    }
}
