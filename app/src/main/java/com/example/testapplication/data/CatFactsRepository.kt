package com.example.testapplication.data

import com.example.testapplication.data.data_source.NetworkDataSource
import com.example.testapplication.data.di.IoDispatcher
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.ui.mvvm.asCatItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CatFactsRepository {
    val catFactsStream: Flow<List<CatItem>>

    suspend fun refreshCatFacts()
}

@OptIn(ExperimentalCoroutinesApi::class)
class NetwCatFactsRepository @Inject constructor(
    private val dataSource: NetworkDataSource,
    private val imageGenerator: ImageGenerator,
    private val errorRepository: ErrorRepository,
    private val networkMonitor: NetworkMonitor,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): CatFactsRepository {

    private val localCatFactsStream = MutableStateFlow<List<CatItem>>(emptyList())

    override val catFactsStream: Flow<List<CatItem>> = flowOf(
        localCatFactsStream,
        flow {
            getRemoteCatFacts()
                .onSuccess { emit(it) }
                .onFailure { throw it }
        }
    ).flowOn(ioDispatcher).flattenMerge()

    override suspend fun refreshCatFacts() {
        if (!networkMonitor.isOnline.first()) {
            errorRepository.sendError(Error.NotConnectedToInternet)
            return
        }
        getRemoteCatFacts()
            .onSuccess { cats ->
                localCatFactsStream.update { cats }
            }
            .onFailure {
                errorRepository.sendError(
                    Error.CatFactsRefreshError("${it.message}")
                )
            }
    }

    private suspend fun getRemoteCatFacts(): Result<List<CatItem>> =
        withContext(ioDispatcher) {
            try {
                Result.success(
                    dataSource.getCatFacts().map {
                        it.asCatItem(imageGenerator)
                    }
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}