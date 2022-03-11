package com.github.tscholze.duobahn.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

private val NAV_RAIL_TOP_SPACING = 32.dp

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SideNavigation(
    navController: NavHostController,
    items: Array<Screen>,
    updateRoute: (String) -> Unit,
) {
    NavigationRail(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Spacer(Modifier.height(NAV_RAIL_TOP_SPACING))
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { item ->
            NavRailItemWithSelector(
                icon = {
                    NavItemIcon(icon = item.icon!!, description = stringResource(item.title!!))
                },
                label = { NavItemLabel(stringResource(item.title!!)) },
                selected = isNavItemSelected(currentDestination, item.route),
                onClick = {
                    navItemOnClick(navController, item.route, updateRoute)
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onPrimary
            )
        }
    }
}
