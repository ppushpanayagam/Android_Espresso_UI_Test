package com.example.testapplication.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.testapplication.R

class MviScreen {

    private val catFactsMviFragment: Int = R.id.catFactsMviFragment

    fun redirectToCatFactsMvi() {

        onView(withId(catFactsMviFragment)).perform(click())
    }
}