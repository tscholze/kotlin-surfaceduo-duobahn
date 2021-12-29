package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.LatLng
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
fun MapView() {

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

                // Setup initial values
                val pickUp = LatLng(48.177696, 11.5916275)

                // Draw markers.
                val markerOptions = MarkerOptions()
                    .title("Microsoft Deutschland GmbH")
                    .position(pickUp)
                map.addMarker(markerOptions)

                // Set camera
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(pickUp, 14f)
                )
            }
        }
    }
}