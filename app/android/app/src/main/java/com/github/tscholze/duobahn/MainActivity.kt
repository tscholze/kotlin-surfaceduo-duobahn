package com.github.tscholze.duobahn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.tscholze.duobahn.ui.pages.MapPage
import com.github.tscholze.duobahn.ui.pages.PreparationPage
import com.github.tscholze.duobahn.ui.pages.SettingsPage
import com.github.tscholze.duobahn.ui.theme.DuoBahnTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuoBahnTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    // App-wide navigation host.
                    NavHost(
                        navController = navController,
                        startDestination = "preparation"
                    ) {
                        // Preparation page.
                        composable("preparation") { PreparationPage(navController) }

                        // Map page.
                        composable("map") { MapPage(navController) }

                        // Settings page.
                        composable("settings") { SettingsPage() }
                    }
                }
            }
        }
    }
}