package com.github.tscholze.duobahn.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Represents the data set of a navigation
 * item.
 */
data class Item(
    @StringRes
    val title: Int,

    @DrawableRes
    val icon: Int,

    val route: String
)
