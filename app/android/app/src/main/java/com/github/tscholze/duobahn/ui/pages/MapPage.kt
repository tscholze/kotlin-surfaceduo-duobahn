package com.github.tscholze.duobahn.ui.pages

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.github.tscholze.duobahn.ui.components.map.MapOverlay
import com.github.tscholze.duobahn.ui.components.map.MapView
import org.koin.androidx.compose.get


/**
 * High level version of MapPage
 *
 * @param repository: Injected `UnprocessedDataRepository` instance.
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MapPage(repository: UnprocessedDataRepository = get()) {

    // MARK: - Properties -

    val context = LocalContext.current
    var currentMarker by remember { mutableStateOf<MarkerDefinition?>(null) }
    val autobahns by remember { mutableStateOf(repository.getAutobahns()) }
    val markers: MutableList<MarkerDefinition> = arrayListOf()

    // Compact all markers into one list.
    autobahns.forEach {
        markers.addAll(it.roadworks)
        markers.addAll(it.webcams)
    }

    MapPage(
        markers = markers,
        onMapClick = { currentMarker = it },
        currentMarker = currentMarker,
        openInMaps = {
            // The feature is based on:
            // https://stackoverflow.com/a/39444675/12871582
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${it.latLng.latitude},${it.latLng.longitude}(${it.title}))")
            ).apply { setPackage("com.google.android.apps.maps") }

            // Open map intent.
            context.startActivity(mapIntent)
        },
        openWeb = {
            // from https://stackoverflow.com/a/9662990/12871582
            // TODO this feels not elegant - maybe we shouldn't use java.net.URL in our domain model?
            try {
                val webIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = it
                }
                context.startActivity(webIntent)
            } catch (e: Throwable) {
                Toast.makeText(
                    context,
                    context.getString(R.string.generic_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )
}


/**
 * Low level version of MapPage:
 * Working on lists and lambdas -> easy to test & understand
 * The compose part is also stateless: state is hoisted in the top-level-wrapper.
 * (This is not true for the non-compose MapView)
 *
 * A map page contains the map itself and its controlling elements.
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MapPage(
    markers: List<MarkerDefinition>,
    onMapClick: (MarkerDefinition?) -> Unit,
    openInMaps: (MarkerDefinition) -> Unit,
    openWeb: (Uri?) -> Unit,
    currentMarker: MarkerDefinition?
) {

    // MARK: - Content -

    Box {
        // Z index: 0
        MapView(
            markers = markers,
            onMapClick = onMapClick
        )

        // Z index 1
        MapOverlay(
            marker = currentMarker,
            modifier = Modifier.padding(32.dp),
            openInMaps = openInMaps,
            openWeb = openWeb,
        )

        // Z index: 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(0.6f))
                .padding(4.dp)
                .align(Alignment.TopStart)
        ) {
            // Spacer with width of NavigationRail.
            Spacer(modifier = Modifier.width(88.dp))

            // Text
            Text(
                stringResource(R.string.app_disclaimer),
                fontSize = 8.sp,
                color = Color.Black,
            )
        }
    }
}