package com.github.tscholze.duobahn.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination


@Composable
fun NavItemIcon(icon: Int, description: String) {
    Icon(painterResource(icon), description)
}

@Composable
fun NavItemLabel(navItem: String) {
    Text(navItem)
}

/**
 * Reference: https://developer.android.com/jetpack/compose/navigation#bottom-nav
 */
@Composable
fun isNavItemSelected(
    currentDestination: NavDestination?,
    navItemRoute: String,
): Boolean {
    return currentDestination?.hierarchy?.any { it.route == navItemRoute } == true
}

/**
 * Reference: https://developer.android.com/jetpack/compose/navigation#bottom-nav
 */
fun navItemOnClick(
    navController: NavController,
    navItem: String,
    updateImageId: (Int?) -> Unit,
    updateRoute: (String) -> Unit,
) {
    // Navigate to new destination
    navController.navigate(navItem) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    // Update current route to new destination
    updateRoute(navItem)

    // Reset selected image when switching gallery
    updateImageId(null)
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ColumnScope.NavRailItemWithSelector(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    selectedContentColor: Color,
    unselectedContentColor: Color,
) {
    Box(modifier = Modifier.weight(1f, fill = false), contentAlignment = Alignment.Center) {
        Column {
            AnimatedVisibility(
                visible = selected,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                Selector()
            }
        }
        Column {
            NavigationRailItem(
                icon = icon,
                label = label,
                selected = selected,
                onClick = onClick,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun RowScope.BottomNavItemWithSelector(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    selectedContentColor: Color,
    unselectedContentColor: Color,
) {
    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center
    ) {
        Row {
            AnimatedVisibility(
                visible = selected,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                Selector()
            }
        }
        Row {
            BottomNavigationItem(
                icon = icon,
                label = label,
                selected = selected,
                onClick = onClick,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor
            )
        }
    }
}

private val DEFAULT_SIZE = 50.dp
private val SelectorShape = RoundedCornerShape(percent = 15)

/**
 * Reference:
 * https://github.com/android/compose-samples/blob/main/Jetsnack/app/src/main/java/com/example/jetsnack/ui/home/Home.kt
 *
 * Creates a graphic that indicates that an item is currently selected.
 */
@Composable
fun Selector() {
    Spacer(
        modifier = Modifier
            .size(DEFAULT_SIZE)
            .background(MaterialTheme.colors.secondary, SelectorShape)
    )
}