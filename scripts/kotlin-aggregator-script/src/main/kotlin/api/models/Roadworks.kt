package api.models

import kotlinx.serialization.Serializable

@Serializable
data class Roadworks(
    val roadworks: List<Roadwork>
)

@Serializable
data class Roadwork(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    val isBlocked: String,
    val point: String,
    val startTimestamp: String,
    val subtitle: String,
    val title: String
)

