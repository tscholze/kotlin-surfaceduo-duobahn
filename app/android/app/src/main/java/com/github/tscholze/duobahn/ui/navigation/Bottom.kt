package com.github.tscholze.duobahn.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Bottom(
    navController: NavHostController,
    items: Array<Screen>,
    updateRoute: (String) -> Unit,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {


        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { item ->
            BottomNavItemWithSelector(
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