package com.example.testapplication.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.testapplication.R

class MvvmScreen {

    private val catFactsMvvmFragment: Int = R.id.catFactsMvvmFragment

    fun redirectToMvvmScreen() {

        onView(withId(catFactsMvvmFragment)).perform(click())
    }
}