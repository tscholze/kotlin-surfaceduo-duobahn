package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.google.maps.android.compose.GoogleMap


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

                    // Disabled to not be covered by the FAB.
                    uiSettings.isZoomControlsEnabled = false
                    uiSettings.isMyLocationButtonEnabled = true

                    // Maybe these are also good.
                    // map.mapType = GoogleMap.MAP_TYPE_SATELLITE
                    // map.isTrafficEnabled = true

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