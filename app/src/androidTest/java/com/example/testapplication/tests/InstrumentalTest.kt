package com.example.testapplication.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapplication.screens.MviScreen
import com.example.testapplication.screens.MvvmScreen
import com.example.testapplication.screens.TestBase
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentalTest: TestBase() {

    private val mvvmScreen = MvvmScreen()
    private val mviScreen = MviScreen()
    @Test
    fun shouldBeAbleToViewAllExpectedFactsOnMviScreen() {

        /*
        * Land on Mvi screen
        * Verify the recycler view list
        * validation -- verify expected items to confirm
        * */

    }

    @Test
    fun shouldBeAbleToRedirectToCatFactsMvvmScreen() {

        /*
        * Redirect to Mvvm screen
        * validation -- verify expected item to confirm
        * */
        mvvmScreen.redirectToMvvmScreen()
    }

    @Test
    fun shouldBeAbleToSelectAnItemOnCatFactsMviScreen() {

        /*
        * Verify first and last item on Mvi screen
        * validation -- verify expected item to confirm
        * */
        mvvmScreen.redirectToMvvmScreen()
        mviScreen.redirectToCatFactsMvi()
    }

}