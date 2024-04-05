package com.example.testapplication.domain

import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.ErrorRepository
import com.example.testapplication.ui.mvvm.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetCatFactsUseCase @Inject constructor(
    private val catFactsRepo: CatFactsRepository,
    private val errorRepo: ErrorRepository,
) {

    operator fun invoke(): Flow<UiState> = flowOf(
        catFactsRepo.catFactsStream
            .map { UiState(catFacts = it) },

        errorRepo.errorFlow
            .map {
                UiState(
                    loadException = it.message,
                    id = UUID.randomUUID()
                )
            }
    ).flattenMerge()

}