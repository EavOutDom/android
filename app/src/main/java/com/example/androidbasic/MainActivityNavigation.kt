package com.example.androidbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.androidbasic.ui.theme.AndroidBasicTheme
import kotlinx.serialization.Serializable

class MainActivityNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenHome,
                ) {
                    composable<ScreenHome> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Button(onClick = { navController.navigate(ScreenShop(total = 10)) }) {
                                Text(text = "Go to screen shop")
                            }
                        }
                    }
                    composable<ScreenShop> {
                        val arg = it.toRoute<ScreenShop>()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(text = "Total: ${arg.total}")
                            Button(onClick = { navController.navigate(ScreenHome) }) {
                                Text(text = "Go to screen home")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenHome

@Serializable
data class ScreenShop(val total: Int)