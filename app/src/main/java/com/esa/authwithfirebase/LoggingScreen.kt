package com.esa.authwithfirebase

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoggingScreen() {
    val logging : LoggingViewModel = hiltViewModel()
}