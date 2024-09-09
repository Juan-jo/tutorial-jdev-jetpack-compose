package com.jdev.jdevcompose.instagramapp.login.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdev.jdevcompose.R


@Preview(
    showSystemUi = true
)
@Composable
fun LoginScreenPrev() {


}

@Composable
fun LoginScreen(
    loginInstagramViewModel: LoginInstagramViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val isLoading by loginInstagramViewModel.isLoading.observeAsState(initial = false)

        if(isLoading) {
            Box(
                Modifier.fillMaxSize()
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), loginInstagramViewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }

}



@Composable
fun Header(modifier: Modifier) {
    //val activity = LocalContext.current as Activity


    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier
            .clickable {
                //activity.finish()
            }
            .padding(top = 12.dp)
    )

}

@Composable
fun Body(
    modifier: Modifier,
    loginInstagramViewModel: LoginInstagramViewModel
) {

    val email: String by loginInstagramViewModel.email.observeAsState("")
    val password: String by loginInstagramViewModel.password.observeAsState("")
    val isLoginEnabled: Boolean by loginInstagramViewModel.isLoginEnable.observeAsState(initial = false)

    Column(
        modifier = modifier
    ) {
        ImageLogo(
            Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(16.dp))
        EmailField(
            email = email,
            onTextChanged = {
                loginInstagramViewModel.onLoginChanged(
                    email = it,
                    password = password)
            }
        )
        Spacer(modifier = Modifier.size(4.dp))
        PasswordField(
            password = password,
            onTextChanged = {
                loginInstagramViewModel.onLoginChanged(
                    email = email,
                    password = it)
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnabled, loginInstagramViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }

}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier
    )
}

@Composable
fun EmailField(
    email: String,
    onTextChanged: (String) -> Unit
) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Email")
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xF2F2F2F2),
            unfocusedContainerColor = Color(0xF2F2F2F2),
        )
    )
}

@Composable
fun PasswordField(
    password: String,
    onTextChanged: (String) -> Unit
) {

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password")
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xF2F2F2F2),
            unfocusedContainerColor = Color(0xF2F2F2F2),
        ),
        trailingIcon = {
            val image = if(passwordVisibility) {
                Icons.Filled.VisibilityOff
            }
            else {
                Icons.Filled.Visibility
            }

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "password")
            }
        },
        visualTransformation = if(passwordVisibility) {
            VisualTransformation.None
        }
        else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ForgotPassword(
    modifier: Modifier
) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun LoginButton(
    loginEnabled: Boolean,
    loginInstagramViewModel: LoginInstagramViewModel
) {

    Button(
        onClick = { loginInstagramViewModel.onLoginSelected() },
        enabled = loginEnabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFF4EA8E9),
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}


@Composable
fun LoginDivider() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        HorizontalDivider(
            Modifier
                .height(1.dp)
                .background(Color(0xFFF9F9F9))
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        HorizontalDivider(
            Modifier
                .height(1.dp)
                .background(Color(0xFFF9F9F9))
                .weight(1f)
        )


    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier =  Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social Login fb",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as jdev",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)

        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {

        HorizontalDivider(
            Modifier
                .height(1.dp)
                .background(Color(0xFFF9F9F9))
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(60.dp))
    }
}

@Composable
fun SignUp() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Dont't have an account",
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5)
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "Sign up.",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}