package com.github.tscholze.duobahn.data.domain.models

import com.google.android.libraries.maps.model.LatLng

// TODO is this really a domain model?

data class MarkerDefinition(
    val title: String,
    val coordinate: LatLng,
    val snippet: String?,
)
