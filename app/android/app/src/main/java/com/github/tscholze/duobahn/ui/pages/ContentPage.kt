package com.github.tscholze.duobahn.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.tscholze.duobahn.R

/**
 * Extended, that means for foldables or
 * spanned dual screen devices optimized content
 * page.
 *
 * @param navController Underlying nav controller.
 */
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
        Box {

            // Selected content
            MapPage()

            // Navigation rail
            NavigationRail(
                selectedItem = selectedItem,
                onSelectItem = { selectedItem = it }
            )
        }

    }
}

/**
 * Centered navigation rail component for given
 * items.
 *
 * @param selectedItem The currently selected nav item.
 * @param onSelectItem Event handler if selected nav item changed.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NavigationRail(
    selectedItem: NavigationItem,
    onSelectItem: (NavigationItem) -> Unit
) {

    // Both spacer will center the items.
    // There is no `menuGravity` parameter
    // as in XML based UI definition
    NavigationRail(
        modifier = Modifier.width(80.dp),
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.8f)
    ) {

        Spacer(Modifier.weight(1f))

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

        Spacer(Modifier.weight(1f))
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