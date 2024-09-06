package com.jdev.jdevcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdev.jdevcompose.ui.theme.JdevComposeTheme
import kotlinx.coroutines.launch

@Preview(
    showSystemUi = true
)
@Composable
fun JdevScaffoldPrev() {
    JdevComposeTheme {
        JdevScaffold()
    }
}

@Composable
fun JdevScaffold() {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MyDrawer()
            }
        },
    ) {
        Scaffold(
            topBar = {
                MyTopAppBar(
                    onClickIcon = { txt ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Has pulsado $txt")
                        }
                    },
                    onClickDrawer = {
                        coroutineScope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }

                    }
                )
            },

            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = { MyBottomNavigation() },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.offset(y = 50.dp),
                    shape = CircleShape,
                    onClick = { }
                ) {
                    Icon(Icons.Default.QrCode, "Large floating action button")
                }

            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { innerPadding ->
            Text(text = "Hola", modifier = Modifier.padding(innerPadding))
        }
    }

}

@Composable
fun MyBottomNavigation() {

    var index by remember {
        mutableStateOf(0)
    }

    BottomAppBar(
        containerColor = Color.LightGray,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { index = 0 }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "home",
                        tint = if (index == 0) Color.Red else Color.DarkGray
                    )
                }
                IconButton(onClick = { index = 1 }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "fav",
                        tint = if (index == 1) Color.Red else Color.DarkGray
                    )
                }
                IconButton(onClick = { index = 2 }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "person",
                        tint = if (index == 2) Color.Red else Color.DarkGray
                    )
                }
                IconButton(onClick = { index = 3 }) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "mic",
                        tint = if (index == 3) Color.Red else Color.DarkGray
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    onClickIcon: (String) -> Unit,
    onClickDrawer:() -> Unit
) {
    TopAppBar(
        title = { Text(text = "Top App Bar") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {
                onClickDrawer()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onClickIcon("Search")
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }

            IconButton(onClick = {
                onClickIcon("Settings")
            }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "settings")
            }
        },
    )
}

@Composable
fun MyDrawer() {

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Opción 1"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Opción 2"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Opción 3"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Opción 4"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Opción 5"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Opción 6"
        )

    }

}