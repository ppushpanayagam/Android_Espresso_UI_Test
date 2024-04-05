package com.example.testapplication.data

import app.cash.turbine.test
import com.example.testapplication.testdoubles.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

data class User(val name: String)

val ana = User("Ana")
val george = User("George")

class UserRepository {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val users = MutableStateFlow(User("unknown"))
    init {
        scope.launch {
            register(ana)
        }
    }
    suspend fun register(user: User) {
        users.emit(user)
    }
    fun getUsers() = users.asStateFlow()
}

class SomeTest {

    @Test
    fun testRegistration() = runTest {
        val subject = UserRepository()

        subject.getUsers().test {
            assertThat(awaitItem().name).isEqualTo("unknown")

            subject.register(ana)
            assertThat(awaitItem()).isEqualTo(ana)

            subject.register(george)
            assertThat(awaitItem()).isEqualTo(george)
        }

        subject.register(ana)
        assertThat(subject.getUsers().value.name).isEqualTo("Ana")
    }


}

@OptIn(ExperimentalCoroutinesApi::class)
class UsingTestDispatchers {

    @get:Rule
    val mainDispatcherRule: MainDispatcherRule = MainDispatcherRule()

    private val testScope = TestScope(mainDispatcherRule.testDispatcher)

    @Test
    fun testWithStandardTestDispatcher() = runTest {
        val sut = UserRepository()
        // default standard test dispatcher
        launch {
            sut.register(ana)
        }
        launch {
            delay(1000)
            sut.register(george)
        }
        advanceUntilIdle()

        assertThat(sut.getUsers().value).isEqualTo(george)
    }

    @Test
    fun testWithUnconfinedTestDispatcher() =
        testScope.runTest {// unconfined test dispatcher
            val sut = UserRepository()

            assertThat(sut.getUsers().value).isEqualTo(ana)

            launch {
                delay(100)
                sut.register(ana)
            }
            launch {
                sut.register(george)
            }
            assertThat(sut.getUsers().value).isEqualTo(george)
        }

}