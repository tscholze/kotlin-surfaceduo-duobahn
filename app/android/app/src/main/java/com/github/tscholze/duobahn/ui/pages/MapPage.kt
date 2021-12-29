package com.github.tscholze.duobahn.ui.pages

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.tscholze.duobahn.R
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import com.github.tscholze.duobahn.ui.components.map.MapView
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue


/**
 * A map page contains the map itself and its controlling elements.
 *
 * @param navController: App-wide navigation controller.
 */
@Composable
fun MapPage(navController: NavController) {

    Box {
        // Z index: 0
        Scaffold(
            floatingActionButton = { MapContentFloatingActionButton(navController) }) {
            MapView()
        }

        // Z index: 1
        Text(
            stringResource(R.string.app_disclaimer),
            fontSize = 8.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(0.6f))
                .padding(4.dp)
                .align(Alignment.TopStart)
        )
    }
}

@Composable
private fun MapContentFloatingActionButton(navController: NavController) {
    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(id = R.string.map_fab_title)) },
        contentColor = Color.White,
        backgroundColor = AutobahnBlue,
        onClick = { navController.navigate("settings") },
        icon = {
            Icon(
            Icons.Default.Settings,
            contentDescription = stringResource(R.string.map_fab_title)
            )
               },
    )
}