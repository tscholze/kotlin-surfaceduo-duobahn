package com.github.tscholze.duobahn.ui.components.structure

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.tscholze.duobahn.ui.flows.PreparationFlow
import com.github.tscholze.duobahn.ui.navigation.Screen
import com.github.tscholze.duobahn.ui.pages.MapPage
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

    // Create navigation handler.
    val navigateTo by rememberSaveable(saver = stateSaver()) { mutableStateOf(Screen.Preparation) }

    // Listen to changes on navigation.
    LaunchedEffect(navigateTo) {
        if (navController.currentDestination?.route != navigateTo.route) {
            navController.navigateTo(navigateTo, clearBackStack = true)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Preparation.route
    ) {
        // Preparation page.
        composable("preparation") { PreparationPage(navController) }

        // Map page.
        composable("map") { MapPage(navController) }

        // Settings page.
        composable("settings") { SettingsPage() }
    }


    // 1. Preparation flow should start
    // 2. after successfully finished ContentFlow should start
    // TODO: Fix it.
    PreparationFlow(navController = navController)
}

// MARK: - NavHostController extension -

fun NavHostController.navigateTo(
    screen: Screen,
    clearBackStack: Boolean = false,
    asSingleTop: Boolean = false
) {
    navigate(screen.route) {
        when {
            clearBackStack -> popUpTo(0)

            asSingleTop -> {
                popUpTo(Screen.Map.route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

// MARK: - Saver extension -

/**
 * Copied from:
 * https://stackoverflow.com/questions/66547601/jetpack-compose-remembersaveable-leads-to-crash-when-an-app-goes-to-background
 */
fun <T> stateSaver() = Saver<MutableState<T>, Any>(
    save = { state -> state.value ?: "null" },
    restore = { value ->
        @Suppress("UNCHECKED_CAST")
        mutableStateOf((if (value == "null") null else value) as T)
    }
)