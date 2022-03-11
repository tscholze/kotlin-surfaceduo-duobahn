package com.github.tscholze.duobahn.ui.components.structure

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import com.github.tscholze.duobahn.ui.flows.PreparationFlow
import com.microsoft.device.dualscreen.windowstate.WindowState


@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun DuoBahnApp(windowState: WindowState) {

    // Create app-wide navigation controller.
    val navController = rememberNavController()


    // 1. Preparation flow should start
    // 2. after successfully finished ContentFlow should start
    // TODO: Fix it.
    PreparationFlow(navController = navController)
}