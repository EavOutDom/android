package com.example.androidbasic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
) {
    data object ScreenHome : BottomNavigationItems(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    )

    data object ScreenBrowse : BottomNavigationItems(
        route = "browse",
        title = "Browse",
        icon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart
    )

    data object ScreenAccount : BottomNavigationItems(
        route = "account",
        title = "Account",
        icon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )
}