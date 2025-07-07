package com.esa.authwithfirebase.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.esa.authwithfirebase.AuthState
import com.esa.authwithfirebase.AuthViewModel

@Composable
fun SignupScreen(
    navController: NavController,
    haveAccont : () -> Unit = {},
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {
                Text(
                    text = "Email"
                )
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(
                    text = "Password"
                )
            }
        )
        Button(
            enabled = authState.value != AuthState.Loading,
            onClick = {
                authViewModel.signup(email = email, password = password)
            }
        ) {
            Text(
                text = "Signup"
            )
        }
        TextButton(
            onClick = {
                haveAccont()
            }
        ) {
            Text(
                text = "have accont? Login"
            )
        }
    }
}