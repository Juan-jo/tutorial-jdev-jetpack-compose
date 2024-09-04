package com.jdev.jdevcompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jdev.jdevcompose.MyRadioButtonList
import com.jdev.jdevcompose.R
import com.jdev.jdevcompose.ui.theme.JdevComposeTheme

@Preview(showSystemUi = true)
@Composable
fun MyDialogPrev() {

    JdevComposeTheme {

        Column(Modifier.fillMaxSize()) {

            MyConfirmationDialog(show = true, onDismiss = {})
        }
    }

}

@Composable
fun MyDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    if (show) {
        AlertDialog(

            title = { Text(text = "Title") },
            text = { Text(text = "Hola, dou una descripcion de dialog :)") },
            confirmButton = {
                TextButton(
                    onClick = { onConfirm() },
                ) {
                    Text(text = "ConfirmButton")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss() },
                ) {
                    Text(text = "DismissButton")
                }
            },
            onDismissRequest = {
                onDismiss()
            },

            )
    }
}

@Composable
fun MySimpleCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {

    if (show) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = true
            )
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
            }
        }
    }
}

@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Set backup account")
                AccountItem(email = "juan@gmail.com", drawable = R.drawable.avatar)
                AccountItem(email = "tadeo.hc@gmail.com", drawable = R.drawable.avatar)
                AccountItem(email = "Add Account", drawable = R.drawable.add)
            }
        }
    }
}

@Composable
fun MyTitleDialog(
    text: String,
    modifier: Modifier = Modifier.padding(bottom = 12.dp)
) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
fun AccountItem(
    email: String,
    @DrawableRes drawable: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))

    }
}


@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {

    if(show) {
        Dialog(onDismissRequest = { /*TODO*/ }) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)) {

                MyTitleDialog(
                    text = "Phone ringtone",
                    modifier = Modifier.padding(24.dp)
                )

                HorizontalDivider(
                    color = Color.LightGray
                )

                var status by remember {
                    mutableStateOf("")
                }
                MyRadioButtonList(status) {
                    status = it
                }

                Row(Modifier.align(Alignment.End).padding(8.dp)) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "CANCEL")
                    }
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Ok")
                    }
                }
            }
        }

    }

}