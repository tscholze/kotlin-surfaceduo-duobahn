package com.github.tscholze.duobahn.data.domain.models

import com.google.android.gms.maps.model.LatLng

// TODO is this really a domain model?

data class MarkerDefinition(
    val type: MarkerType,
    val title: String,
    val coordinate: LatLng,
    val snippet: String?,
) {
    enum class MarkerType { WEBCAM, ROADWORK }
}
