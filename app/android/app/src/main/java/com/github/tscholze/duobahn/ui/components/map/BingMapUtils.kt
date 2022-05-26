package com.github.tscholze.duobahn.ui.components.map

import com.microsoft.maps.GeoboundingBox
import com.microsoft.maps.Geopoint
import com.microsoft.maps.MapIcon

fun MapMarkerModel.toGeopoint(): Geopoint = Geopoint(lat, lng)

fun GeoboundingBox.isInBounds(marker: MapMarkerModel): Boolean =
    (marker.lng in west..east && marker.lat in south..north)

class MapIconSelectable(val bingModel: MapIcon, var isSelected: Boolean = false)

data class MapMarkerModel(
    val name: String,
    val type: MarkerType,
    val lat: Double,
    val lng: Double,
    val hasCity: Boolean? = null,
    val id: Long = 0

)

enum class MarkerType { PIN, CIRCLE, CENTER }