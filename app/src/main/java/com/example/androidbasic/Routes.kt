package com.example.androidbasic

sealed class Routes(val route: String) {
    data object Welcome : Routes("ScreenWelcome")
}