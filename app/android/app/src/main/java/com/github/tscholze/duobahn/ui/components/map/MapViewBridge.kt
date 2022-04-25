package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.domain.models.Roadwork
import com.github.tscholze.duobahn.data.domain.models.Webcam
import com.github.tscholze.duobahn.data.domain.models.toLngLat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*


/**
 * Composes a map view.
 *
 * to get started with this component, it's best to look at the official compose map docs:
 * https://github.com/googlemaps/android-maps-compose and their sample app
 *
 */
@Composable
fun MapView(
    markers: Iterable<MarkerDefinition>,
    onMapClick: (MarkerDefinition?) -> Unit,
) {

    // MARK: - State properties -

    // Observing and controlling the camera's state can be done with a CameraPositionState
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markers.first().coordinate.toLngLat(), 11f)
    }

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
        ),
        cameraPositionState = cameraPositionState,
        onMapClick = { onMapClick(null) }
    ) {
        markers.forEach { markerDefinition ->
            Marker(
                position = markerDefinition.coordinate.toLngLat(),
                icon = when (markerDefinition) {
                    is Webcam -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_webcam)
                    is Roadwork -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_roadwork)
                    else -> throw NoWhenBranchMatchedException("Please define me!")
                },
                onClick = {
                    onMapClick(markerDefinition)

                    // returning false here allows the standard onClick to proceed:
                    // - centering the map
                    // - showing an infoWindow. This is not visible tho, since marker title / snippet is not set
                    false
                }
            )
        }

    }
}