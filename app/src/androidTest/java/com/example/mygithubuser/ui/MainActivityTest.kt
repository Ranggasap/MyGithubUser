package com.example.mygithubuser.ui

import android.content.res.Resources
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.screenshot.captureToBitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mygithubuser.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    private val dummyTextDarkMode = "Dark Mode"

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertChangeThemePageNavigation(){
        onView(withId(R.id.theme_mode))
            .perform(click())

        onView(withId(R.id.switch_theme))
            .check(matches(withText(dummyTextDarkMode)))

    }
}