package com.example.testapplication.data.data_source

import com.example.testapplication.data.data_source.model.ApiResponseItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

fun interface ApiService {
    @GET("facts")
    suspend fun getCatFacts(): List<ApiResponseItem>
}

interface NetworkDataSource : ApiService

class NetworkDataSourceImpl @Inject constructor(): NetworkDataSource {

    private val api =
        Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    override suspend fun getCatFacts(): List<ApiResponseItem> =
        api.getCatFacts()

}