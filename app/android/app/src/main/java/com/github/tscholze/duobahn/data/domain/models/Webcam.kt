package com.github.tscholze.duobahn.data.domain.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition.MarkerType.WEBCAM
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
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
fun Webcam.toMarkerDefinition() =
    MarkerDefinition (
        type = WEBCAM,
        title = name,
        snippet = direction,
        coordinate = coordinate.toLngLat(),
    )

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

fun MarkerOptions.icon(context: Context, @DrawableRes vectorDrawable: Int): MarkerOptions {
    this.icon(ContextCompat.getDrawable(context, vectorDrawable)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    })
    return this
}