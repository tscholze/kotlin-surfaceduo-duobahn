package com.github.tscholze.duobahn.ui.flows

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.tscholze.duobahn.ui.pages.PreparationPage

@Composable
fun PreparationFlow(navController: NavHostController) {
    PreparationPage(navController = navController)
}