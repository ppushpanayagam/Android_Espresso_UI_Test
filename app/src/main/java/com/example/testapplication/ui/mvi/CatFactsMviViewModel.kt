package com.example.testapplication.ui.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class CatFactsMviViewModel @Inject constructor(
    private val catStateStore: CatsStateStore
): ViewModel() {

    fun onViewCreated(lifecycle: Lifecycle, view: CatFactsMviFragment) {
        bind(lifecycle = lifecycle.asEssentyLifecycle(), mode = BinderLifecycleMode.START_STOP) {
            catStateStore.states.distinctUntilChanged() bindTo view::render
            catStateStore.labels.distinctUntilChanged() bindTo view::oneOffEvent
            view.events.distinctUntilChanged() bindTo catStateStore
        }
    }

    override fun onCleared() {
        super.onCleared()
        catStateStore.dispose()
    }
}