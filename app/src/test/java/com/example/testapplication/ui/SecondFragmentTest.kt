package com.example.testapplication.ui

import com.example.testapplication.ui.mvvm.CatFactsMvvmFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class SecondFragmentTest {

    private lateinit var subject: CatFactsMvvmFragment

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        subject = CatFactsMvvmFragment()
        hiltRule.inject()
    }

    @Test
    fun secondFragment_initialized() {
        assertNotNull(subject)

    }

}