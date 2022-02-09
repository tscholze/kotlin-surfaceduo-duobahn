package com.github.tscholze.duobahn.ui.components.structure

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

    // Foundation layer of the app.
    Box(modifier = Modifier.fillMaxSize()) {
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