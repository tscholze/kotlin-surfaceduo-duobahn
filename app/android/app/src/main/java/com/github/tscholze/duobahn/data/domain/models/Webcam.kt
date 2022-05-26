package com.github.tscholze.duobahn.data.domain.models

import com.github.tscholze.duobahn.R
import com.microsoft.maps.Geopoint
import java.net.URL

/**
 * Represents a webcam.
 *
 * @property id Unique identifier.
 * @property title Human-readable name of the roadwork.
 * @property direction Point of view direction
 * @property coordinate Location of webcam
 * @property thumbnailURL Url to the current thumbnail
 * @property linkURL to the actual camera feed.
 */
data class Webcam(
    override val id: String,
    override val title: String,
    override val location: Geopoint,
    override val imageId: Int = R.drawable.ic_map_webcam,

    val direction: String,
    val thumbnailURL: URL?,
    val thumbnailUrlString: String,
    val linkURL: URL?
) : Mapable

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
        title = title,
        direction = subtitle,
        location = coordinate.toModel().toGeoPoint(),
        thumbnailURL = thumbnailURL,
        thumbnailUrlString = imageurl,
        linkURL = linkURL
    )
}