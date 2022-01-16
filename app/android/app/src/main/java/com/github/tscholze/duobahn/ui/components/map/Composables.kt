package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition.MarkerType.ROADWORK
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition.MarkerType.WEBCAM
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
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
fun MapView(markers: List<MarkerDefinition>) {

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
                                        ROADWORK -> BitmapDescriptorFactory.fromResource(R.drawable.ic_map_camera)
                                    }
                                )
                        )
                    }

                    // Set camera
                    moveCamera(CameraUpdateFactory.newLatLngZoom(markers.first().coordinate, 14f))
                }
            }
        }
    }
}