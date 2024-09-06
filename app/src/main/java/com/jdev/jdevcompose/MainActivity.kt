package com.jdev.jdevcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jdev.jdevcompose.ui.CheckInfo
import com.jdev.jdevcompose.ui.MyConfirmationDialog
import com.jdev.jdevcompose.ui.MyCustomDialog
import com.jdev.jdevcompose.ui.MyDialog
import com.jdev.jdevcompose.ui.MySimpleCustomDialog
import com.jdev.jdevcompose.ui.NavRoutes
import com.jdev.jdevcompose.ui.Screen1
import com.jdev.jdevcompose.ui.Screen2
import com.jdev.jdevcompose.ui.Screen3
import com.jdev.jdevcompose.ui.Screen4
import com.jdev.jdevcompose.ui.Screen5
import com.jdev.jdevcompose.ui.theme.JdevComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JdevComposeTheme {
                Surface(
                    contentColor = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = NavRoutes.Screen1.route
                    ) {
                        composable(NavRoutes.Screen1.route) { Screen1(navigationController) }
                        composable(NavRoutes.Screen2.route) { Screen2(navigationController) }
                        composable(NavRoutes.Screen3.route) { Screen3(navigationController) }
                        composable(
                            route = NavRoutes.Screen4.route,
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navigationController = navigationController,
                                age = backStackEntry.arguments?.getInt("age") ?: 0
                            )
                        }
                        composable(
                            route = NavRoutes.Screen5.route,
                            arguments = listOf(navArgument("name") { defaultValue = "jdev" })
                        ) { backStackEntry ->
                            Screen5(
                                navigationController = navigationController,
                                name = backStackEntry.arguments?.getString("name")
                            )
                        }

                    }
                }
                //JdevScaffold()
                //SuperHeroStinckyView()
                //SuperHeroView()

                //TwitterPostScreen()
                //LoginScreen()
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android - ",
                        modifier = Modifier.padding(innerPadding)
                    )
                    MyColumn()
                }*/
            }
        }
    }

    @Composable
    private fun Components() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            var myName by remember {
                mutableStateOf("")
            }

            var selected by rememberSaveable {
                mutableStateOf("jdev")
            }


            val options = getOptions(listOf("Jdev", "Goku", "Vegenta"))

            var showDialog by remember {
                mutableStateOf(false)
            }

            var showCustomDialog by remember {
                mutableStateOf(false)
            }

            var showAccountDialog by remember {
                mutableStateOf(false)
            }

            var showConfirmDialog by remember {
                mutableStateOf(false)
            }

            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {

                MySpacer(size = 30)
                MyTextField(name = myName) {
                    myName = it
                }

                MySpacer(size = 10)
                MyName(name = myName)

                MySpacer(size = 5)
                MyDropDownMenu()
                MySpacer(size = 10)
                MyButton()
                MySpacer(size = 10)
                MyImage()
                MySpacer(size = 10)
                MyIcon()
                MySpacer(size = 10)
                MyProgress()
                MySpacer(size = 10)
                MySwitch()
                MySpacer(size = 10)
                MyCheckbox()
                MySpacer(size = 10)
                MyTriStatusCheckBox()
                MySpacer(size = 10)
                options.forEach {
                    MyCheckboxComplex(it)
                }
                MySpacer(size = 10)
                MyRadioButton()
                MySpacer(size = 10)
                MyRadioButtonList(selected) { selected = it }
                MySpacer(size = 5)
                MyCard()

                MySpacer(size = 5)
                MyBadgeBox()
                MySpacer(size = 5)
                MyDivider()
                MySpacer(size = 5)
                BasicSlider()
                MySpacer(size = 5)

                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = { showDialog = true }) {
                        Text(text = "Mostrar Dialog")
                    }
                    MyDialog(
                        show = showDialog,
                        onDismiss = { showDialog = false },
                        onConfirm = {
                            Log.i("jdev", "click onConfirm")
                        })
                }
                MySpacer(size = 5)
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = { showCustomDialog = true }) {
                        Text(text = "Mostrar Custom Dialog")
                    }
                    MySimpleCustomDialog(
                        show = showCustomDialog,
                        onDismiss = { showCustomDialog = false })
                }

                MySpacer(size = 5)
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    Button(onClick = { showAccountDialog = true }) {
                        Text(text = "Dialog Account")
                    }

                    MyCustomDialog(show = showAccountDialog, onDismiss = {
                        showAccountDialog = false
                    })
                }

                MySpacer(size = 5)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    OutlinedButton(onClick = {
                        showConfirmDialog = true
                    }) {
                        Text(text = "Dialog Confirm")
                    }

                    MyConfirmationDialog(
                        show = showConfirmDialog,
                        onDismiss = {
                            showConfirmDialog = false
                        }
                    )

                }

                MySpacer(size = 90)

            }

        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name! :)", modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JdevComposeTheme {
        Column {
            MyBadgeBox()
            MyDivider()
            MyDropDownMenu()
            BasicSlider()
        }
    }
}


@Composable
fun BasicSlider() {

    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var completeValue by remember { mutableStateOf("0") }

    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = {
                completeValue = sliderPosition.toString()
            },
            valueRange = 0f..10f,
            steps = 9
        )
        Text(text = completeValue)
    }

}

@Composable
fun MyDropDownMenu() {

    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val desserts = listOf("Helado", "Chocolate", "Café", "Fruta")

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }

        )

        DropdownMenu(
            expanded = expanded, onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(
                    text = { Text(text = dessert) },
                    onClick = {
                        expanded = false
                        selectedText = dessert
                    })
            }
        }

    }
}

@Composable
fun MyDivider() {

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Red
    )

}

@Composable
fun MyBadgeBox() {

    var itemCount by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("$itemCount")
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Shopping cart",
            )
        }
        Button(onClick = { itemCount++ }) {
            Text("Add item")
        }
    }


}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = Color.Red,
            containerColor = Color.Yellow
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Hola 1")
            Text(text = "Hola 2")
            Text(text = "Hola 3")
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {


    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "jdev", onClick = { onItemSelected("jdev") })
            Text(text = "jdev")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Tadeo", onClick = { onItemSelected("Tadeo") })
            Text(text = "Tadeo")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Gaby", onClick = { onItemSelected("Gaby") })
            Text(text = "Gaby")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Eithan", onClick = { onItemSelected("Eithan") })
            Text(text = "Eithan")
        }
    }

}


@Composable
fun MyRadioButton() {
    Row {
        Text(text = "Hola", Modifier.padding(top = 12.dp))
        RadioButton(
            selected = false,
            onClick = { /*TODO*/ },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Red
            )
        )
    }
}

@Composable
fun MyTriStatusCheckBox() {

    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }

    TriStateCheckbox(
        state = status,
        onClick = {
            status = when (status) {
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
            }
        }
    )

}

@Composable
fun MyCheckboxComplex(checkInfo: CheckInfo) {

    Row {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = {
                checkInfo.onCheckedChange(!checkInfo.selected)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                checkmarkColor = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title, modifier = Modifier.padding(top = 12.dp))
    }

}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {

    return titles.map {
        var state by rememberSaveable {
            mutableStateOf(false)
        }

        CheckInfo(
            title = it,
            selected = state,
            onCheckedChange = { newStatus -> state = newStatus }
        )
    }
}

@Composable
fun MyCheckbox() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }

    Checkbox(
        checked = state,
        onCheckedChange = {
            state = !state
        },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            checkmarkColor = Color.LightGray
        )
    )
}

@Composable
fun MySwitch() {

    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Switch(
        checked = state,
        onCheckedChange = {
            state = !state
        },
        enabled = true,
        /*colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Green,
            checkedThumbColor = Color.Yellow
        )*/

    )
}

@Composable
fun MyProgress() {

    var progressValue by rememberSaveable {
        mutableStateOf(0f)
    }

    Column(
        Modifier.padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            progress = {
                progressValue
            },
            color = Color.Red,
            strokeWidth = 8.dp,
        )
        LinearProgressIndicator(
            Modifier.padding(top = 32.dp),
            color = Color.Red,
        )
        MySpacer(size = 5)
        Row {
            Button(
                onClick = { progressValue += 0.1f },
                Modifier.padding(8.dp)
            ) {
                Text(text = "Incrementar")
            }
            Button(
                onClick = { progressValue -= 0.1f },
                Modifier.padding(8.dp)
            ) {
                Text(text = "Reducir")
            }
        }
    }
}


// Icons
// https://fonts.google.com/ add for more icons graddle Module
@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "icon",
        tint = Color.LightGray
    )
}

// Box
@Composable
fun MyBox(name: String) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Box")
        }
    }
}

// Column
@Composable
fun MyColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Column 1", modifier = Modifier
                .background(Color.Blue)
                .height(100.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Column 2", modifier = Modifier
                .background(Color.Red)
                .height(100.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Column 3", modifier = Modifier
                .background(Color.Cyan)
                .height(100.dp)
        )
        Text(
            text = "Column 4", modifier = Modifier
                .background(Color.LightGray)
                .height(100.dp)
        )
        Text(
            text = "Column 5", modifier = Modifier
                .background(Color.DarkGray)
                .height(100.dp)
        )
        Text(
            text = "Column 1", modifier = Modifier
                .background(Color.Blue)
                .height(100.dp)
        )
        Text(
            text = "Column 2", modifier = Modifier
                .background(Color.Red)
                .height(100.dp)
        )
        Text(
            text = "Column 3", modifier = Modifier
                .background(Color.Cyan)
                .height(100.dp)
        )
        Text(
            text = "Column 4", modifier = Modifier
                .background(Color.LightGray)
                .height(100.dp)
        )
        Text(
            text = "Column 5", modifier = Modifier
                .background(Color.DarkGray)
                .height(100.dp)
        )

    }


}

// Rows
@Composable
fun MyRows() {

    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Row 1")
        Text(text = "Row 2")
        Text(text = "Row 3")
    }
}

// My Layout
@Composable
fun MyComplexLayout() {

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan), contentAlignment = Alignment.Center
        ) {

            Text(text = "Ejemplo 1")
        }
        MySpacer(30)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.DarkGray), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo 2")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Green), contentAlignment = Alignment.Center
            ) {
                Text(text = "hola jdev")
            }

        }
        MySpacer(30)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Magenta), contentAlignment = Alignment.BottomCenter
        ) {

            Text(text = "Ejemplo 4")
        }
    }

}

@Composable
private fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}


@Composable
fun MyStateExample() {
    var counter by rememberSaveable {
        mutableStateOf(0)
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1 }) {
            Text(text = "Pulsar")
        }
        Text(text = "He pulsado $counter veces")
    }
}


@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Texto ejemplo")
        Text(text = "Texto ejemplo", color = Color.Blue)
        Text(text = "Texto ejemplo", color = Color.Blue, fontWeight = FontWeight.ExtraBold)
        Text(text = "Texto ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Texto ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(text = "Texto ejemplo", style = TextStyle(textDecoration = TextDecoration.LineThrough))
        Text(text = "Texto ejemplo", style = TextStyle(textDecoration = TextDecoration.Underline))
        Text(
            text = "Texto ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                )
            )
        )
        Text(text = "Texto ejemplo Size", fontSize = 26.sp)
    }
}

@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {

    OutlinedTextField(value = name, onValueChange = {
        onValueChanged(it)
    }, Modifier.padding(24.dp), label = {
        Text(
            text = "Your name"
        )
    }, colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.DarkGray,
        unfocusedBorderColor = Color.Red
    )
    )


}

@Composable
fun MyName(name: String) {
    Text(text = "valor $name")
}


@Composable
fun MyButton() {

    var enabled by rememberSaveable {
        mutableStateOf(true)
    }

    Column {
        MySpacer(size = 25)
        Button(
            onClick = {
                Log.i("jdev", "on click")
                enabled = false
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.LightGray
            ),
            border = BorderStroke(0.5.dp, Color.Green),
            enabled = enabled
        ) {
            Text(text = "Hola Button")
        }
        MySpacer(size = 5)
        OutlinedButton(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Yellow,
            )
        ) {
            Text(text = "Hola Outline")
        }
        MySpacer(size = 5)
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Hola TextButton")
        }
    }

}

@Composable
fun MyImage() {

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        alpha = 0.5f,
        //modifier = Modifier.clip(RoundedCornerShape(25f))
        modifier = Modifier
            .clip(CircleShape)
            .border(1.dp, Color.Red, CircleShape)

    )
}