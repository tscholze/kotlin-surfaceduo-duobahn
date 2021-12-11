package api.models

import kotlinx.serialization.Serializable

@Serializable
data class Coordinate(
    val lat: String,
    val long: String
)