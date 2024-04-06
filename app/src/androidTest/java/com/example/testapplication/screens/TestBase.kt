package com.example.testapplication.screens

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.testapplication.ui.MainActivity
import org.junit.Rule

open class TestBase {

    lateinit var scenario: ActivityScenario<MainActivity>
    private val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)

    @get:Rule
    val activityRule = ActivityScenarioRule<MainActivity>(intent)

}