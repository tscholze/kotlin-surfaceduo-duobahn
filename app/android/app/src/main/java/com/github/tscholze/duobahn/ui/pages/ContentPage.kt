package com.github.tscholze.duobahn.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue

@Composable
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun ContentPage() {

    // MARK: - Properties -

    val openDialog = remember { mutableStateOf(true) }

    // MARK: - Content -

    // Content container
    Box {

        // Z-Index: 0
        // Map view
        MapView()

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
                onClick = {
                    openDialog.value = true
                }
            )

            DBFloatingActionButton(
                icon = Icons.Rounded.Info,
                onClick = { /*TODO*/ }
            )
        }

        // Z-Index: 3
        // Optional dialog / modal
        Modal(
            showModal = openDialog.value,
            onClose = { openDialog.value = false }
        )
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

@Composable
private fun Modal(showModal: Boolean, onClose: () -> Unit) {
    if (showModal) {
        Dialog(
            onDismissRequest = { onClose() }
        ) {
            Surface(
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
            ) {
                // Content container
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {

                    // Header
                    Row(modifier = Modifier.align(Alignment.Start)) {
                        0
                        // Spacer
                        Spacer(modifier = Modifier.weight(1f))

                        // Close button
                        Button(onClick = onClose) {
                            Icon(Icons.Default.Close, contentDescription = "Localized description")
                        }
                    }

                    AboutContent()
                }
            }
        }
    }
}

@Composable
private fun AboutContent() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "About")
    }

}
