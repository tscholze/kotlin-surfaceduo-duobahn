package com.github.tscholze.duobahn.data.domain.models

import com.google.android.gms.maps.model.LatLng
import com.microsoft.maps.Geopoint

/**
 * Represents a coordinate.
 *
 * @property lat Latitude value.
 * @property long Longitude value.
 */
data class Coordinate(
    val lat: Double,
    val long: Double
)

// MARK: - From Mapper -

/**
 * Gets model from dto.
 */
fun com.github.tscholze.duobahn.data.network.dto.Coordinate.toModel() =
    Coordinate(
        lat = lat.toDouble(),
        long = long.toDouble()
    )


// MARK: - To Mapper -

/**
 * Gets the coordinate as MapView'able LatLng value
 */
fun Coordinate.toLngLat() = LatLng(lat, long)

/**
 * Gets the coordinate as Bing Map LatLng value
 */
fun Coordinate.toGeoPoint() = Geopoint(lat, long)