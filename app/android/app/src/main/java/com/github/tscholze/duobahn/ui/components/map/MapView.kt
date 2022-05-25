package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.github.tscholze.duobahn.BuildConfig.CREDENTIALS_KEY
import com.microsoft.maps.Geopoint
import com.microsoft.maps.MapAnimationKind
import com.microsoft.maps.MapScene
import com.microsoft.maps.MapView
import kotlinx.coroutines.launch

@Composable
fun BingMap(lat: Double, lng: Double, zoom: Double) {
    val map = rememberBingMapViewWithLifecycle()
    BingMapContainer(map = map, lat = lat, lng = lng, zoom = zoom)
}

@Composable()
fun BingMapContainer(map: MapView, lat: Double, lng: Double, zoom: Double) {

    LaunchedEffect(map) {
        val point = Geopoint(lat, lng)
        map.setScene(MapScene.createFromLocation(point), MapAnimationKind.NONE)
    }

    val coroutineScope = rememberCoroutineScope()

    AndroidView(factory = { map }) {
        coroutineScope.launch {
            it.centerTo(lat = lat, lng = lng, zoom = zoom)
        }
    }
}

@Composable
private fun rememberBingMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val map = remember { MapView(context) }
    map.setCredentialsKey(CREDENTIALS_KEY)

    val observer = rememberMapboxViewLifecycleObserver(map)
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
private fun rememberMapboxViewLifecycleObserver(map: MapView): LifecycleEventObserver {
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

fun MapView.centerTo(lat: Double, lng: Double, zoom: Double) {
    val point = Geopoint(lat, lng)
    setScene(MapScene.createFromLocation(point), MapAnimationKind.NONE)
}