package com.example.testapplication.ui

import app.cash.turbine.test
import com.example.testapplication.data.CatFactsRepository
import com.example.testapplication.data.ErrorRepository
import com.example.testapplication.data.ImageGenerator
import com.example.testapplication.domain.GetCatFactsUseCase
import com.example.testapplication.testdoubles.FakeErrorRepository
import com.example.testapplication.testdoubles.MainDispatcherRule
import com.example.testapplication.testdoubles.sampleCatsList
import com.example.testapplication.testdoubles.sampleCatsList2
import com.example.testapplication.testdoubles.sampleCatsList3
import com.example.testapplication.ui.mvvm.CatFactsViewModel
import com.example.testapplication.ui.mvvm.UiState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub
import kotlin.test.assertIs

@RunWith(JUnit4::class)
class CatFactsViewModelMockTest {

    private lateinit var viewModel: CatFactsViewModel

    @Mock
    lateinit var catFactsRepository: CatFactsRepository

    @Mock
    lateinit var errorRepository: ErrorRepository

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var testCoroutineRule: MainDispatcherRule = MainDispatcherRule()



    @Before
    fun setup() {
        catFactsRepository.stub {
            onBlocking { catFactsStream } doAnswer { getSampleFlow() }
        }
        viewModel = CatFactsViewModel(
            catFactsRepo = catFactsRepository,
            getCatFactsUseCase = GetCatFactsUseCase(catFactsRepository, errorRepository)
        )
    }

    private fun getSampleFlow() = flow {
        emit(sampleCatsList)
        delay(100)
        emit(sampleCatsList2)
        delay(200)
        emit(sampleCatsList3)
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertIs<UiState>(viewModel.catFacts.value)
    }

    @Test
    fun stateIsSuccess() = runTest {
        viewModel.catFacts.test {
            val firstList = awaitItem()

            // assert state is success
            assertIs<UiState>(firstList)

            val secondList = awaitItem()
            assertIs<UiState>(secondList)
            assertThat(secondList).isEqualTo(sampleCatsList)

            val thirdList = awaitItem()
            assertIs<UiState>(thirdList)
            assertThat(thirdList).isEqualTo(sampleCatsList2)

            expectNoEvents()
        }
    }

}