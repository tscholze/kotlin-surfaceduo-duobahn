package com.github.tscholze.duobahn.data.domain.models

import com.github.tscholze.duobahn.R
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.MarkerOptions
import java.net.URL

/**
 * Represents a webcam.
 *
 * @property id Unique identifier.
 * @property name Human-readable name of the roadwork.
 * @property direction Point of view direction
 * @property coordinate Location of webcam
 * @property thumbnailURL Url to the current thumbnail
 * @property linkURL to the actual camera feed.
 */
data class Webcam(
    val id: String,
    val name: String,
    val direction: String,
    val coordinate: Coordinate,
    val thumbnailURL: URL?,
    val linkURL: URL?
)

// MARK: - To Mapper -

/**
 * Maps model to marker.
 */
fun Webcam.toMarkerOptions(): MarkerOptions {

    return MarkerOptions()
        .title(name)
       // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_webcam))
        .position(coordinate.toLngLat())
}

// MARK: - From Mapper -

/**
 * Gets model from dto.
 */
fun com.github.tscholze.duobahn.data.network.dto.Webcam.toModel(): Webcam {

    var thumbnailURL: URL? = null
    var linkURL:URL? = null
    try { thumbnailURL = URL(imageurl) } catch(e: Exception) {}
    try { linkURL = URL(linkurl) } catch(e: Exception) {}

    return Webcam(
        id = identifier,
        name = title,
        direction = subtitle,
        coordinate = coordinate.toModel(),
        thumbnailURL = thumbnailURL,
        linkURL = linkURL
    )
}
