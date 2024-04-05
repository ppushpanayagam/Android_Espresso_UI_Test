package com.example.testapplication.testdoubles

import com.example.testapplication.data.data_source.model.ApiResponseItem
import com.example.testapplication.data.data_source.NetworkDataSource
import javax.inject.Inject

class FakeDataSource @Inject constructor(): NetworkDataSource {
    override suspend fun getCatFacts(): List<ApiResponseItem> = emptyList()
}