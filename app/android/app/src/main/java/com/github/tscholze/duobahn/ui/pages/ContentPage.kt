package com.github.tscholze.duobahn.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.tscholze.duobahn.R

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExtendedContentPage(navController: NavController) {

    // MARK: - Properties -

    var selectedItem by remember { mutableStateOf(NavigationItem.MAP) }

    // MARK: - Content -

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name_long)) }
            )
        }
    ) {
        Row {
            // Navigation rail
            // TODO: Center it vertically
            Box(contentAlignment = Alignment.Center) {
                NavigationRail(
                    selectedItem = selectedItem,
                    onSelectItem = { selectedItem = it }
                )
            }

            // Selected content
            MapPage()
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NavigationRail(
    selectedItem: NavigationItem,
    onSelectItem: (NavigationItem) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.width(80.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        for (item in NavigationItem.values()) {
            NavigationRailItem(
                selected = selectedItem == item,
                onClick = { onSelectItem.invoke(item) },
                icon = { Icon(item.icon, item.title) },
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.primary,
                label = { Text(item.title, modifier = Modifier.padding(top = 8.dp)) }
            )
        }
    }
}


private enum class NavigationItem(
    val title: String,
    val icon: ImageVector
) {

    MAP(
        title = "Map",
        icon = Icons.Filled.Dashboard
    ),

    ABOUT(
        title = "About",
        icon = Icons.Filled.Info
    ),

    SETTINGS(
        title = "Settings",
        icon = Icons.Filled.Settings
    )
}