package com.github.tscholze.duobahn.ui.components.map

import android.graphics.BitmapFactory
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.github.tscholze.duobahn.BuildConfig.CREDENTIALS_KEY
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.microsoft.maps.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get


// MARK: - Internal composable views -

@Composable
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
            image =
                MapImage(BitmapFactory.decodeResource(LocalContext.current.resources, it.imageId))
        }

        mapLayer.elements.add(pin)
    }

    // Set map to container
    BingMapContainer(map = map)
}

// MARK: - Private composable views -

@Composable()
private fun BingMapContainer(map: MapView) {
    LaunchedEffect(map) {
        // map.setScene(MapScene.createFromLocation(point), MapAnimationKind.NONE)
    }

    val coroutineScope = rememberCoroutineScope()
    AndroidView(factory = { map }) {
        coroutineScope.launch {
            // it.centerTo(lat = lat, lng = lng)
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

// https://github.com/microsoft/surface-duo-dual-screen-experience-example/blob/main/app/src/emulator/java/com/microsoft/device/samples/dualscreenexperience/presentation/store/map/BingMapController.kt