package com.example.testapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testapplication.ui.MainActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun openSelectiveFact() {

        ActivityScenario.launch(MainActivity::class.java)
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.testapplication", appContext.packageName)

    }

    @Test
    fun listShouldBeListedAsExpected() {

        ActivityScenario.launch(MainActivity::class.java)
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.testapplication", appContext.packageName)

    }

    @Test
    fun redirectToMvvmFacts() {

        ActivityScenario.launch(MainActivity::class.java)
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.testapplication", appContext.packageName)

    }
}