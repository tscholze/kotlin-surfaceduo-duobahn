package api.models

import kotlinx.serialization.Serializable

@Serializable
data class Webcams(
    val webcam: List<Webcam>
)

@Serializable
data class Webcam(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    val imageurl: String,
    val isBlocked: String,
    val linkurl: String,
    val `operator`: String,
    val point: String,
    val subtitle: String,
    val title: String
)