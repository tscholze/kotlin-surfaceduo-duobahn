package com.github.tscholze.duobahn.data.domain.models

import com.google.android.gms.maps.model.LatLng

interface MarkerDefinition{
    val title: String
    val coordinate: Coordinate;

    val latLng: LatLng
        get() = coordinate.toLngLat()
}
