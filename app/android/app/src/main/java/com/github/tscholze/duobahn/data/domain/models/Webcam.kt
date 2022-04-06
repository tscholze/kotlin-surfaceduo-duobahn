package com.github.tscholze.duobahn.data.domain.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import java.net.URI
import java.net.URL

/**
 * Represents a webcam.
 *
 * @property id Unique identifier.
 * @property title Human-readable name of the roadwork.
 * @property direction Point of view direction
 * @property coordinate Location of webcam
 * @property thumbnailURL Url to the current thumbnail
 * @property linkUri to the actual camera feed.
 */
data class Webcam(
    val id: String,
    override val title: String,
    val direction: String,
    override val coordinate: Coordinate,
    val thumbnailURL: URL?,
    val thumbnailUrlString: String,
    val linkUri: Uri?
): MarkerDefinition

// MARK: - From Mapper -

/**
 * Gets model from dto.
 */
fun com.github.tscholze.duobahn.data.network.dto.Webcam.toModel(): Webcam {

    var thumbnailURL: URL? = null
    var linkUri: Uri? = null

    try { thumbnailURL = URL(imageUrl) } catch(e: Exception) {}
    try { linkUri = Uri.parse(linkUrl) } catch(e: Exception) {}

    return Webcam(
        id = identifier,
        title = title,
        direction = subtitle,
        coordinate = coordinate.toModel(),
        thumbnailURL = thumbnailURL,
        thumbnailUrlString = imageUrl,
        linkUri = linkUri
    )
}