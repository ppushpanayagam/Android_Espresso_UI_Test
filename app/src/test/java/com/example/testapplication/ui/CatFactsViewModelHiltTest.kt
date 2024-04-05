package com.example.testapplication.ui

import app.cash.turbine.test
import com.example.testapplication.data.ImageGenerator
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.domain.GetCatFactsUseCase
import com.example.testapplication.testdoubles.FakeCatFactsRepository
import com.example.testapplication.testdoubles.FakeErrorRepository
import com.example.testapplication.testdoubles.sampleCatsList
import com.example.testapplication.ui.mvvm.CatFactsViewModel
import com.example.testapplication.ui.mvvm.UiState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class CatFactsViewModelHiltTest {

    private lateinit var viewModel: CatFactsViewModel

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var catFactsRepository: FakeCatFactsRepository

    @Inject
    lateinit var errorRepository: FakeErrorRepository

    @Inject
    lateinit var imageGenerator: ImageGenerator


    @Before
    fun setup() {
        hiltRule.inject()
        assertNotNull(catFactsRepository)

        viewModel = CatFactsViewModel(
            catFactsRepo = catFactsRepository,
            getCatFactsUseCase = GetCatFactsUseCase(catFactsRepository, errorRepository)
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertIs<UiState>(viewModel.catFacts.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun stateIsSuccessful() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.catFacts.collect()
        }

        // Emit items
        catFactsRepository.emit(sampleCatsList)

        // Assert new value is Success
        assertIs<UiState>(viewModel.catFacts.value)

        collectJob.cancel()
    }

    @Test
    fun collectStateWithTurbine() = runTest {
        viewModel.catFacts.test {
            catFactsRepository.emit(sampleCatsList)
            // assert state is success
            assertIs<UiState>(awaitItem())

            val items = (awaitItem() as UiState).catFacts
            assertEquals(
                actual = items.map(CatItem::text),
                expected = sampleCatsList.map(CatItem::text)
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

   /* @Test
    fun stateIsException() = runTest {
        viewModel.catFacts.test {
            catFactsRepository.emit(getThrowable())

            // assert state is success
            assertIs<UiState.Exception>(awaitItem())

            val exceptionMessage = (awaitItem() as UiState.Exception).throwable.message
            assertEquals("broken", exceptionMessage)
        }
    }*/

}