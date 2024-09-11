package com.jdev.jdevcompose.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class JdevComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myFirstTest() {

        composeTestRule.setContent {
            JdevComponent()
        }

        //FINDER
        composeTestRule.onNodeWithText("jdev", ignoreCase = true)
        composeTestRule.onNodeWithTag("component1")

    }


}