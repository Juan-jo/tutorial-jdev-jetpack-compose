package com.jdev.jdevcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun Screen1(navigationController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(
            text = "Screen 1",
            modifier = Modifier.align(Alignment.Center).clickable {
                navigationController.navigate(NavRoutes.Screen2.route)
            }
        )
    }
}

@Composable
fun Screen2(navigationController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(
            text = "Screen 2",
            modifier = Modifier.align(Alignment.Center).clickable {
                navigationController.navigate(NavRoutes.Screen3.route)
            }
        )
    }
}

@Composable
fun Screen3(navigationController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Screen 3",
            modifier = Modifier.align(Alignment.Center).clickable {
                navigationController.navigate(NavRoutes.Screen4.createRoute(29))
            }
        )
    }
}

@Composable
fun Screen4(
    navigationController: NavHostController,
    age: Int
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Text(
            text = "Edad $age",
            modifier = Modifier.align(Alignment.Center).clickable {
                navigationController.navigate(NavRoutes.Screen5.createRoute("Tadeo"))

            }
        )
    }
}

@Composable
fun Screen5(
    navigationController: NavHostController,
    name: String?
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Text(
            text = "Nombre: $name",
            modifier = Modifier.align(Alignment.Center).clickable {
                navigationController.navigate(NavRoutes.Screen1.route)
            }
        )
    }
}

sealed class NavRoutes(val route: String) {
    data object Screen1: NavRoutes("screen1")
    data object Screen2: NavRoutes("screen2")
    data object Screen3: NavRoutes("screen3")
    data object Screen4: NavRoutes("screen4/{age}") {
        fun createRoute(age: Int) = "screen4/$age"
    }
    data object Screen5: NavRoutes("screen5?name={name}") {
        fun createRoute(name: String) = "screen5?name=$name"
    }
}