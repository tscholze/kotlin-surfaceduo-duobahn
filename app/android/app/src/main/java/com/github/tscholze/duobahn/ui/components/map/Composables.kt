package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition.MarkerType.ROADWORK
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition.MarkerType.WEBCAM
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*


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

    // Observing and controlling the camera's state can be done with a CameraPositionState
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markers.first().coordinate, 11f)
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
    ) {
        markers.forEach {
            Marker(
                title = it.title,
                snippet = it.snippet,
                position = it.coordinate,
                icon = when (it.type) {
                    WEBCAM -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_webcam)
                    ROADWORK -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_roadwork)
                }
            )
        }

    }
}