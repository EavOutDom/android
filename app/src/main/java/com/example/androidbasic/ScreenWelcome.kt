package com.example.androidbasic

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ScreenWelcome(navController: NavController) {
    Box() {
        Button(onClick = { navController.navigate(BottomNavigationItems.ScreenHome.route) }) {
            Text(text = "Go to screen home")
        }
    }
}