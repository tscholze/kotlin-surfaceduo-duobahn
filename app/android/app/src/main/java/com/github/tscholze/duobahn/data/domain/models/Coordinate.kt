package com.github.tscholze.duobahn.data.domain.models

import com.google.android.libraries.maps.model.LatLng

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