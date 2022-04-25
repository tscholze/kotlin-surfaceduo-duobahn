package com.github.tscholze.duobahn.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.github.tscholze.duobahn.ui.components.map.MapView
import org.koin.androidx.compose.get


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
fun MapView(repository: UnprocessedDataRepository = get()) {

    // MARK: - Properties -

    val aggregator by remember { mutableStateOf(repository.getMarkers()) }
    var currentMarker by remember { mutableStateOf<MarkerDefinition?>(null) }

    // MARK: - Content -

    Scaffold {
        Box {
            // Z index: 0
            MapView(
                markers = aggregator.markers,
                onMapClick = { currentMarker = it }
            )

            // Z index: 1
            Text(
                stringResource(R.string.app_disclaimer),
                fontSize = 8.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(0.6f))
                    .padding(4.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}