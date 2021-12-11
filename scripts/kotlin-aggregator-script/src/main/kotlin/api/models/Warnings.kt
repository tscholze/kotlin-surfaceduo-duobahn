package api.models

import kotlinx.serialization.Serializable

@Serializable
data class Warnings(
    val warning: List<Warning>
)

@Serializable
data class Warning(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    val isBlocked: String,
    val point: String,
    val startTimestamp: String,
    val subtitle: String,
    val title: String
)