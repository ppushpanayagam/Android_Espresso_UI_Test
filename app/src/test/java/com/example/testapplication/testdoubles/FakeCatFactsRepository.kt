package com.example.testapplication.testdoubles

import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.Error
import com.example.testapplication.data.model.CatItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FakeCatFactsRepository @Inject constructor(): CatFactsRepository {

    private val _catFacts = MutableStateFlow<List<CatItem>>(emptyList())

    override val catFactsStream: Flow<List<CatItem>> = _catFacts.asStateFlow()
    override suspend fun refreshCatFacts() {
        // TODO()
    }

    suspend fun emit(items: List<CatItem>) {
        _catFacts.emit(items)
    }
}