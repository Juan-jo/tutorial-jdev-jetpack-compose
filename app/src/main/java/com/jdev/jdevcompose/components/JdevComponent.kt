package com.jdev.jdevcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag


@Composable
fun JdevComponent() {

    Column {
        Text(text = "JDEV", modifier = Modifier.testTag("component1"))
        Text(text = "TADEO", modifier = Modifier.testTag("component2"))
    }
}