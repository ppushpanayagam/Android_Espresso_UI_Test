package com.example.testapplication.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.ImageGenerator
import com.example.testapplication.data.data_source.model.ApiResponseItem
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.domain.GetCatFactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CatFactsViewModel @Inject constructor(
    getCatFactsUseCase: GetCatFactsUseCase,
    private val catFactsRepo: CatFactsRepository,
) : ViewModel() {

    val catFacts: StateFlow<UiState> =
        getCatFactsUseCase()
            .catch {
                emit(UiState(loadException = it.message))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState(isLoadingCatFacts = true)
            )

    fun refresh() {
        viewModelScope.launch {
            catFactsRepo.refreshCatFacts()
        }
    }

}

data class UiState(
    val isLoadingCatFacts: Boolean = false,
    val catFacts: List<CatItem> = emptyList(),
    val loadException: String? = null,
    /**
     * Id of the state. Required for the cases when we want to emit multiple times the same state.
     * e.g. fetch fail with the same message
     * */
    val id: UUID = UUID.randomUUID()
) {
    val hasFailedLoading
        get() = !loadException.isNullOrEmpty()
}

fun ApiResponseItem.asCatItem(imageGenerator: ImageGenerator) = CatItem(
    text = this.text ?: "",
    image = imageGenerator.get()
)
