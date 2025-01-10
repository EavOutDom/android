package com.example.androidbasic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onBottomBarVisibilityChanged: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Welcome.route
    ) {
        composable(Routes.Welcome.route) {
            onBottomBarVisibilityChanged(false)
            ScreenWelcome(navController = navController)
        }
        composable(BottomNavigationItems.ScreenHome.route) {
            onBottomBarVisibilityChanged(true)
            ScreenHome()
        }
        composable(BottomNavigationItems.ScreenBrowse.route) {
            onBottomBarVisibilityChanged(true)
            ScreenBrowse()
        }
        composable(BottomNavigationItems.ScreenAccount.route) {
            onBottomBarVisibilityChanged(true)
            ScreenAccount()
        }
    }
}