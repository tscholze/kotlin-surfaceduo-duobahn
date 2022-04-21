package com.github.tscholze.duobahn.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
@Composable
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
fun ExtendedContentPage(navController: NavController) {

    // MARK: - Properties -

    // Selected navigation item state.
    var selectedItem by remember { mutableStateOf(NavigationItem.MAP) }

    // Bottom sheet state.
    // Default value: Collapsed
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )

    // MARK: - Content -

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { SheetContent() },
        topBar = { AppBar() }
    ) {
        PageContent(
            selectedItem = selectedItem,
            onSelectItem = { selectedItem = it }
        )
    }
}

/**
 * App's app bar.
 */
@Composable
private fun AppBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name_long)) }
    )
}

/**
 * Page content structure.
 *
 * @param selectedItem The currently selected nav item.
 * @param onSelectItem Event handler if selected nav item changed.
 */
@Composable
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
private fun PageContent(
    selectedItem: NavigationItem,
    onSelectItem: (NavigationItem) -> Unit
) {
    Box {

        // Selected content
        MapPage()

        // Navigation rail
        NavigationRail(
            selectedItem = selectedItem,
            onSelectItem = { onSelectItem(it) }
        )
    }
}

/**
 * Bottom sheet content related on given data origin.
 */
@Composable
private fun SheetContent() {
    Text(text = "Foo")
}

/**
 * Centered navigation rail component for given
 * items.
 *
 * @param selectedItem The currently selected nav item.
 * @param onSelectItem Event handler if selected nav item changed.
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun NavigationRail(
    selectedItem: NavigationItem,
    onSelectItem: (NavigationItem) -> Unit
) {

    // Both spacer will center the items.
    // There is no `menuGravity` parameter
    // as in XML based UI definition
    NavigationRail(
        modifier = Modifier.width(80.dp),
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 1f)
    ) {

        Spacer(Modifier.weight(1f))

        for (item in NavigationItem.values()) {
            NavigationRailItem(
                selected = selectedItem == item,
                onClick = { onSelectItem.invoke(item) },
                icon = { Icon(item.icon, item.title) },
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                label = { Text(item.title, modifier = Modifier.padding(top = 8.dp)) }
            )
        }

        Spacer(Modifier.weight(1f))
    }
}

/**
 * Contains all available user-accessible navigation items.
 * Will be used in navigation rail and bottom bar
 */
private enum class NavigationItem(

    /** Title of the item */
    val title: String,

    /** Image resource of the corresponding icon */
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