package com.example.testapplication.data.di

import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.ConnectivityManagerNetworkMonitor
import com.example.testapplication.data.ErrorRepository
import com.example.testapplication.data.NetworkMonitor
import com.example.testapplication.data.data_source.NetworkDataSource
import com.example.testapplication.testdoubles.FakeCatFactsRepository
import com.example.testapplication.testdoubles.FakeDataSource
import com.example.testapplication.testdoubles.FakeErrorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ReposModule::class]
)
interface FakeReposModule {

    @Binds
    @Singleton
    fun bindsDataSource(impl: FakeDataSource): NetworkDataSource

    @Binds
    @Singleton
    fun bindsCatFactsRepo(impl: FakeCatFactsRepository): CatFactsRepository

    @Binds
    @Singleton
    fun bindsErrorRepo(impl: FakeErrorRepository): ErrorRepository

    @Binds
    @Singleton
    fun bindsNetwMonitor(impl: ConnectivityManagerNetworkMonitor): NetworkMonitor
}