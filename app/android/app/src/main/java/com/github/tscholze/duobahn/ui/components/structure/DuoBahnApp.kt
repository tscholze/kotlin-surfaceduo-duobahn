package com.github.tscholze.duobahn.ui.components.structure

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.tscholze.duobahn.ui.pages.ContentPage
import com.github.tscholze.duobahn.ui.pages.PreparationPage
import com.github.tscholze.duobahn.ui.pages.SettingsPage
import com.microsoft.device.dualscreen.windowstate.WindowState


@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun DuoBahnApp(windowState: WindowState) {

    // Create app-wide navigation controller.
    val navController = rememberNavController()

    // Foundation layer of the app.
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "preparation"
        ) {
            // Preparation page.
            composable("preparation") { PreparationPage(navController) }

            // Map page.
            composable("content") { ContentPage() }

            // Settings page.
            composable("settings") { SettingsPage() }
        }
    }
}