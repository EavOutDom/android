package com.example.androidbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.androidbasic.ui.theme.AndroidBasicTheme

class MainActivityBottomNav : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicTheme {
                val navController: NavHostController = rememberNavController()
                var bottomVisible by remember { mutableStateOf(true) }

                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
                    bottomBar = {
                        if (bottomVisible) {
                            BottomBar(
                                navController = navController,
                                modifier = Modifier
                            )
                        }
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavigationGraph(
                            navController = navController,
                            onBottomBarVisibilityChanged = { bottomVisible = it }
                        )
                    }
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    Scaffold(
//        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
//        bottomBar = { MyBottomNavigation(navController = navController) }
//    ) { paddingValues ->
//        Box(modifier = modifi)
//    }
//}
//
//@Composable
//fun MyBottomNavigation(navController: NavHostController) {
//
//}
