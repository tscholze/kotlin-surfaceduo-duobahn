package com.github.tscholze.duobahn.ui.components.map

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.github.tscholze.duobahn.BuildConfig.CREDENTIALS_KEY
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.microsoft.maps.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get


// MARK: - Internal composable views -

/**
 * Compose wrapper for a Microsoft Bing Map View.
 */
@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun BingMap(repository: UnprocessedDataRepository = get()) {

    // Get map
    val map = rememberBingMapViewWithLifecycle()

    // Get pin data source
    val aggregator by remember { mutableStateOf(repository.getMarkers()) }

    // Create pin map layer
    val mapLayer = MapElementLayer()
    map.layers.add(mapLayer)

    // Add all pins
    aggregator.markers.forEach {
        val pin = MapIcon().apply {
            tag = it.id
            title = it.title
            location = it.location
            image = MapImage(
                BitmapFactory.decodeResource(
                    LocalContext.current.resources,
                    it.imageId
                )
            )
            flyout = MapFlyout().apply {
                title = it.title
                description =
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum "
            }
        }

        mapLayer.elements.add(pin)
    }

    // Set map to container
    BingMapContainer(map = map)


    // Check for permissions
    // Location permission
    val permission = rememberPermissionState(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    when (permission.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {
            map.userLocation.startTracking(
                GPSMapLocationProvider.Builder(LocalContext.current).build()
            )
            map.userLocation.trackingMode = MapUserLocationTrackingMode.CENTERED_ON_USER
        }
        is PermissionStatus.Denied -> {
            val openDialog = remember { mutableStateOf(true) }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = { Text(text = stringResource(R.string.alert_location_request_title)) },
                    text = { Text(stringResource(R.string.alert_location_request_message)) },
                    buttons = {
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            // Cancel button
                            TextButton(onClick = { openDialog.value = false }) {
                                Text(stringResource(R.string.misc_no))
                            }

                            // Ok button
                            TextButton(onClick = { permission.launchPermissionRequest() }) {
                                Text(stringResource(R.string.misc_yes))
                            }
                        }
                    }
                )
            }
        }
    }
}

// MARK: - Private composable views -

@Composable()
private fun BingMapContainer(map: MapView) {
    LaunchedEffect(map) {
        //
    }

    val coroutineScope = rememberCoroutineScope()
    AndroidView(factory = { map }) {
        coroutineScope.launch {
            //
        }
    }
}

@Composable
private fun rememberBingMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val map = remember { MapView(context) }
    map.setCredentialsKey(CREDENTIALS_KEY)

    val observer = rememberBingMapViewLifecycleObserver(map)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return map
}

@Composable
private fun rememberBingMapViewLifecycleObserver(map: MapView): LifecycleEventObserver {
    return remember(map) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                else -> Unit // nop
            }
        }
    }
}

// MARK: - Extension -

fun MapView.centerTo(lat: Double, lng: Double) {
    val point = Geopoint(lat, lng)
    setScene(MapScene.createFromLocation(point), MapAnimationKind.NONE)
}
