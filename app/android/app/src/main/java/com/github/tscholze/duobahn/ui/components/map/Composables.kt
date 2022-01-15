package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Composes a map view.
 *
 * TODO: Show marks, etc.
 */
@Composable
fun MapView(markers: List<MarkerOptions>) {

    // MARK: - State properties -

    val mapView = rememberMapViewWithLifecycle()

    // MARK: - UI -

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AndroidView({ mapView }) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {

                // Load & setup map
                val map = mapView.awaitMap()

                // Disabled to not be covered by the FAB.
                map.uiSettings.isZoomControlsEnabled = false
                map.uiSettings.isMyLocationButtonEnabled = true

                // Maybe these are also good.
                // map.mapType = GoogleMap.MAP_TYPE_SATELLITE
                // map.isTrafficEnabled = true

                // Draw markers.
                markers.forEach { map.addMarker(it) }

                // Set camera
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(markers.first().position, 14f)
                )
            }
        }
    }
}