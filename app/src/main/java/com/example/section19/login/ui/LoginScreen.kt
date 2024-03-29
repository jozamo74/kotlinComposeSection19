package com.example.section19.login.ui

import android.app.Activity
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.section19.R

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    Box( modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                CircularProgressIndicator()
            }
        }
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), viewModel)
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }

}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = Color(0xFFb5b5b5))
        Text(
            text = "Sign up.",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun Body(modifier: Modifier, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")
    val isLoginEnable: Boolean by viewModel.isLoginEnabled.observeAsState(initial = false)

    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            viewModel.onLoginChanged(it, pass)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(pass) {
            viewModel.onLoginChanged(email, it)

        }
        Spacer(modifier = Modifier.size(8.dp))
        Forgotpassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable, viewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social login fb",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as JZM",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR", modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFb5b5b5)
        )
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, viewModel: LoginViewModel) {
    Button(
        onClick = { viewModel.onLoginSelected() },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2B9BEB),
            disabledContainerColor = Color(0xFF4EA8E9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = " Log In",
        )
    }
}

@Composable
fun Forgotpassword(modifier: Modifier) {
    Text(
        text = "Forget password",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun Password(pass: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    TextField(value = pass,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Red,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFEBE9E9),
            unfocusedContainerColor = Color(0xFFEBE9E9),
            focusedTextColor = Color(0xFF828282),
            unfocusedTextColor = Color(0xFF828282),
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                R.drawable.ic_visibility
            } else {
                R.drawable.ic_visibility_off
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon( imageVector = ImageVector.vectorResource(image), contentDescription = "show password")
            }
        },
        visualTransformation =
        if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email")},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Red,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFEBE9E9),
            unfocusedContainerColor = Color(0xFFEBE9E9),
            focusedTextColor = Color(0xFF828282),
            unfocusedTextColor = Color(0xFF828282),
        )
    )
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
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() })
}

