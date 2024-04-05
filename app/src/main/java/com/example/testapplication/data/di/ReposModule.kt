package com.example.testapplication.data.di

import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.ConnectivityManagerNetworkMonitor
import com.example.testapplication.data.DefaultErrorRepository
import com.example.testapplication.data.ErrorRepository
import com.example.testapplication.data.NetwCatFactsRepository
import com.example.testapplication.data.NetworkMonitor
import com.example.testapplication.data.data_source.NetworkDataSource
import com.example.testapplication.data.data_source.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface ReposModule {

    @Binds
    @Singleton
    fun bindsDataSource(impl: NetworkDataSourceImpl): NetworkDataSource

    @Binds
    @Singleton
    fun bindsCatFactsRepo(impl: NetwCatFactsRepository): CatFactsRepository

    @Binds
    @Singleton
    fun bindsErrorRepo(impl: DefaultErrorRepository): ErrorRepository

    @Binds
    @Singleton
    fun bindsNetwMonitor(impl: ConnectivityManagerNetworkMonitor): NetworkMonitor

}