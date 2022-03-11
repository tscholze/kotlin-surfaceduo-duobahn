package com.github.tscholze.duobahn.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.tscholze.duobahn.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null,
    @DrawableRes val iconInactive: Int? = null,
) {

    object Preparation : Screen(
        route = "preparation",
    )

    object Map : Screen(
        route = "map",
        title = R.string.navigation_map_route_id,
        icon = R.drawable.ic_map_misc,
        iconInactive = R.drawable.ic_map_webcam,
    )
}