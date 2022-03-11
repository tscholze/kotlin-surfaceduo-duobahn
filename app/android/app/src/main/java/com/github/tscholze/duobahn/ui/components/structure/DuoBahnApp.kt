package com.github.tscholze.duobahn.ui.components.structure

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.ui.navigation.Item
import com.github.tscholze.duobahn.ui.navigation.SideNavigation
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

    /*
    // Extract window state information
    val isDualScreen = windowState.isDualScreen()
    val isDualPortrait = windowState.isDualPortrait()
    val isDualLandscape = windowState.isDualLandscape()
    val foldSize = windowState.foldSize.dp

    // Set up starting route for navigation in pane 1
    var currentRoute by rememberSaveable { mutableStateOf("navDestinations[0].route") }
    val updateRoute: (String) -> Unit = { newRoute -> currentRoute = newRoute }

    // Set up variable to store selected image id
    var imageId: Int? by rememberSaveable { mutableStateOf(null) }
    val updateImageId: (Int?) -> Unit = { newId -> imageId = newId }

    TwoPaneLayout(
        paneMode = TwoPaneMode.HorizontalSingle,
        pane1 = {
            Pane1(isDualScreen, isDualPortrait, imageId, updateImageId, currentRoute, updateRoute)
        },
        pane2 = {
            Pane2(isDualPortrait, isDualLandscape, foldSize, imageId, updateImageId, currentRoute)
        },
    )

    // If only one pane is being displayed, make sure the correct pane is displayed
    if (!isDualPortrait) {
        if (imageId != null)
            navigateToPane2()
        else
            navigateToPane1()
    }*/
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Pane1(
    isDualScreen: Boolean,
    isDualPortrait: Boolean,
    imageId: Int?,
    updateImageId: (Int?) -> Unit,
    currentRoute: String,
    updateRoute: (String) -> Unit
) {
    Text(text = "Pane1")
    //ShowWithNav(isDualScreen, isDualPortrait, imageId, updateImageId, currentRoute, updateRoute)
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun Pane2(
    isDualPortrait: Boolean,
    isDualLandscape: Boolean,
    foldSize: Dp,
    imageId: Int?,
    updateImageId: (Int?) -> Unit,
    currentRoute: String,
) {
    Text(text = "Pane2")
}