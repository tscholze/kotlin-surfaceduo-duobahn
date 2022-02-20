package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


/**
 * An about page that helps the user to understand why
 * the app exists and where to find further information
 *
 * @param navController: App-wide navigation controller.
 */
@Composable
@ExperimentalMaterialApi
fun MapPage(
    navController: NavController
) {
    // MARK: - Content -

    Column(
        Modifier
            .fillMaxHeight()
            .background(White)
    ) {

        // Scrollview
        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            // Logo

            // Abstract

            // Links

            // Long read
            Text("Foo", Modifier.padding(16.dp))
        }
    }
}