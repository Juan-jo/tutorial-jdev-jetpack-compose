package com.jdev.jdevcompose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt


@Preview
@Composable
fun ColorAnimation() {

    var secondColor by rememberSaveable {
        mutableStateOf(false)
    }
    val realColorSecond by animateColorAsState(
        targetValue = if (secondColor) Color.Red else Color.Yellow,
        label = "ok",
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        Modifier
            .size(200.dp)
            .background(realColorSecond)
            .clickable {
                secondColor = !secondColor
            }
    )
}

@Preview
@Composable
fun AnimationSize() {

    var smallSize by rememberSaveable {
        mutableStateOf(true)
    }

    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 500
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Cyan)
            .clickable {
                smallSize = !smallSize
            }
    )
}

@Preview
@Composable
fun VisibilityAnimation() {

    var isVisible by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(50.dp))

        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar/Ocultar")
        }

        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}


@Preview
@Composable
fun CrossfadeAnimation() {
    var myTypeComponent: ComponentType by rememberSaveable {
        mutableStateOf(ComponentType.Text)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(50.dp))
        Button(onClick = {
            myTypeComponent = getComponentTypeRandom()
        }) {
            Text(text = "Change Component")
        }
        Spacer(modifier = Modifier.size(10.dp))

        Crossfade(targetState = myTypeComponent, label = "") {
            when (it) {
                ComponentType.Image -> Icon(imageVector = Icons.Default.Nfc, contentDescription = "")
                ComponentType.Text -> Text(text = "Soy un texto")
                ComponentType.Box -> Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Green)
                )

                ComponentType.Error -> Text(text = "Error...")
            }
        }


    }
}

fun getComponentTypeRandom(): ComponentType {

    return when (nextInt(from = 0, until = 3)) {
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> ComponentType.Error
    }
}

enum class ComponentType() {
    Image, Text, Box, Error
}