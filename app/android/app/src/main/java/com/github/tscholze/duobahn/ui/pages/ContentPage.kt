package com.github.tscholze.duobahn.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.tscholze.duobahn.ui.components.map.BingMap
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue

@Composable
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun ContentPage() {

    // MARK: - Content -

    // Content container
    Box {

        // Z-Index: 0
        // Map view
        BingMap()

        // Z-Index: 1
        // Floating actions buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            DBFloatingActionButton(
                icon = Icons.Rounded.Settings,
                onClick = { /*TODO*/ }
            )

            DBFloatingActionButton(
                icon = Icons.Rounded.Info,
                onClick = { /*TODO*/ }
            )
        }
    }
}


@Composable
private fun DBFloatingActionButton(
    onClick: () -> Unit,
    icon: ImageVector
) {
    FloatingActionButton(
        backgroundColor = AutobahnBlue,
        contentColor = Color.White,
        onClick = onClick,
        content = { Icon(icon, contentDescription = "Localized description") }
    )
}