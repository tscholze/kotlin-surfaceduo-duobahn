package api.models

import kotlinx.serialization.Serializable

@Serializable
data class Closures(
    val closure: List<Closure>
)

@Serializable
data class Closure(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
   // val impact: Impact?,
    val isBlocked: String,
    val point: String,
    val startTimestamp: String,
    val subtitle: String,
    val title: String
)

@Serializable
data class Impact(
    val lower: String,
    val symbols: List<String>,
    val upper: String
)
