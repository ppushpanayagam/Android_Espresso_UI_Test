package com.example.testapplication.ui.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.di.MainDispatcher
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.ui.mvi.CatsStateStore.OneOffEvent
import com.example.testapplication.ui.mvi.CatsStateStore.ScreenState
import com.example.testapplication.ui.mvi.CatsStateStore.UserIntent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface CatsStateStore : Store<UserIntent, ScreenState, OneOffEvent> {

    sealed class UserIntent {
        data class CatItemClicked(val item: CatItem) : UserIntent()
    }

    data class ScreenState(
        val isLoading: Boolean = false,
        val items: List<CatItem> = emptyList(),
        val hasError: Boolean = false,
        val errorMessage: String? = null
    )

    sealed class OneOffEvent {
        data class OpenImageFragment(val item: CatItem) : OneOffEvent()
    }
}

class CatsStateStoreFactory @Inject constructor(
    private val factory: StoreFactory,
    private val repository: CatFactsRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) {

    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): CatsStateStore = object : CatsStateStore, Store<UserIntent, ScreenState, OneOffEvent> by factory.create(
        name = "secondFragmentStore",
        autoInit = true,
        initialState = ScreenState(),
        bootstrapper = coroutineBootstrapper {
           dispatch(Action.LoadInitialState)
        },
        executorFactory = coroutineExecutorFactory<UserIntent, Action, ScreenState, Message, OneOffEvent>(mainDispatcher) {
            onIntent<UserIntent> {intent ->
                when (intent) {
                    is UserIntent.CatItemClicked -> {
                        publish(OneOffEvent.OpenImageFragment(intent.item))
                    }
                }
            }
            onAction<Action.LoadInitialState> {
                dispatch(Message.Loading)
                repository.catFactsStream
                    .onEach { dispatch(Message.NewList(it)) }
                    .catch {  dispatch(Message.Error(it.message)) }
                    .launchIn(this)
            }
        },
        reducer = { msg ->
            when (msg) {
                is Message.Loading -> copy(isLoading = true)
                is Message.NewList -> copy(isLoading = false, items = msg.cats)
                is Message.Error -> copy(isLoading = false, hasError = true, errorMessage = msg.message)
            }
        }
    ) {}

    sealed class Action {
        data object LoadInitialState: Action()
    }

    sealed class Message {
        data object Loading: Message()
        data class NewList(val cats: List<CatItem>): Message()
        data class Error(val message: String?): Message()
    }

}


