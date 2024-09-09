package com.jdev.jdevcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showSystemUi = true
)
@Composable
fun PrevButtons() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Hello")
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        Button(
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.Green
            ),
            enabled = false,
            onClick = { /*TODO*/ }) {
            Text(text = "Hello")
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = "FilledTonalButton")
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "ElevatedButton")
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = Color.DarkGray
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "")
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            text = { Text(text = "ExtendedFloatingActionButton")},
            icon = { Icon(Icons.Filled.Favorite, "")}
        )

    }

}