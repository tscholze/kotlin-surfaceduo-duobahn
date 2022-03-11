package com.github.tscholze.duobahn.ui.flows

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.ui.navigation.Item
import com.github.tscholze.duobahn.ui.navigation.SideNavigation
import com.github.tscholze.duobahn.ui.pages.MapPage
import com.github.tscholze.duobahn.ui.pages.PreparationPage
import com.github.tscholze.duobahn.ui.pages.SettingsPage

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentFlow(navController: NavHostController) {

    // Make navigation items
    val items = arrayOf(
        Item(
            title = R.string.navigation_map_route_id,
            icon = R.drawable.ic_map_misc,
            route = stringResource(id = R.string.navigation_map_route_id)
        ),

        Item(
            title = R.string.navigation_settings_route_id,
            icon = R.drawable.ic_map_misc,
            route = stringResource(id = R.string.navigation_settings_route_id)
        ),

        Item(
            title = R.string.navigation_about_route_id,
            icon = R.drawable.ic_map_misc,
            route = stringResource(id = R.string.navigation_about_route_id)
        )
    )

    // Store and handle navigation route values.
    var currentRoute by rememberSaveable { mutableStateOf(items.first().route) }
    val updateRoute: (String) -> Unit = { newRoute -> currentRoute = newRoute }


    // Foundation layer of the app.
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(R.string.scaffold_title)) }) }) {
        Row(modifier = Modifier.fillMaxSize()) {

            SideNavigation(
                navController = navController,
                items = items,
                updateRoute = updateRoute
            )

            NavHost(
                modifier = Modifier.onGloballyPositioned {
                    if (navController.currentDestination?.route != currentRoute) {
                        try {
                            navController.navigate(currentRoute)
                        } catch (e: NullPointerException) {
                            // Nav graph may be null if this is the first run through
                            Log.i(
                                "DuoBahn",
                                "Caught the following exception: ${e.message}"
                            )
                        }
                    }
                },
                navController = navController,
                startDestination = currentRoute
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