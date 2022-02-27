package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.google.android.gms.maps.GoogleMapOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings


/**
 * Composes a map view.
 *
 * to get started with this component, it's best to look at the official compose map docs:
 * https://github.com/googlemaps/android-maps-compose and their sample app
 *
 * TODO: Show marks, etc.
 */
@Composable
fun MapView(markers: List<MarkerDefinition>) {

    // MARK: - State properties -

    // MARK: - UI -

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(
            // Maybe these are also good.
            //  mapType = MapType.SATELLITE,
            // isTrafficEnabled = true
        ),
        uiSettings = MapUiSettings(
            // Disabled to not be covered by the FAB.
            zoomControlsEnabled = false,
            myLocationButtonEnabled = true,
            // Maybe these are also good.
            //compassEnabled = true,
        )
    )

    /*
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AndroidView({ mapView }) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {

                // Load & setup map
                mapView.awaitMap().apply {

                    // Draw markers.
                    markers.forEach {
                        addMarker(
                            MarkerOptions()
                                .title(it.title)
                                .snippet(it.snippet)
                                .position(it.coordinate)
                                .icon(
                                    when (it.type) {
                                        WEBCAM -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_webcam)
                                        ROADWORK -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_roadwork)
                                    }
                                )
                        )
                    }

                    // Set camera
                    moveCamera(CameraUpdateFactory.newLatLngZoom(markers.first().coordinate, 14f))
                }
            }
        }
    }*/
}