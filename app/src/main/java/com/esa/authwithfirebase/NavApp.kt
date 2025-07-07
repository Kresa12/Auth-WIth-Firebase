package com.esa.authwithfirebase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esa.authwithfirebase.screen.HomeScreen
import com.esa.authwithfirebase.screen.LoginScreen
import com.esa.authwithfirebase.screen.SignupScreen

@Composable
fun NavApp(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){ LoginScreen(
            navController = navController,
            dontHaveAccont = {navController.navigate("signup")},
            authViewModel = authViewModel
        ) }
        composable("signup"){ SignupScreen(
            navController = navController,
            haveAccont = {navController.navigate("login")},
            authViewModel = authViewModel
        ) }
        composable("home"){ HomeScreen(
            navController = navController,
            authViewModel = authViewModel
        ) }
    })
}