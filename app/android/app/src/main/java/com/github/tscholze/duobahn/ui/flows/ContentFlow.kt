package com.github.tscholze.duobahn.ui.flows

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentFlow(navController: NavHostController) {

    /*
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

     */
}