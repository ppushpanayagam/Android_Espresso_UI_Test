package com.example.testapplication.ui.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.testapplication.ui.mvi.CatsStateStore
import com.example.testapplication.ui.mvi.CatsStateStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideStoreFactory(): StoreFactory = LoggingStoreFactory(DefaultStoreFactory())

    @Provides
    @ViewModelScoped
    fun provideStore(
        factory: CatsStateStoreFactory
    ): CatsStateStore = factory.create()
}