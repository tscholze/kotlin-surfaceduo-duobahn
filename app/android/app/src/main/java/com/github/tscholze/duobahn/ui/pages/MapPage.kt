package com.github.tscholze.duobahn.ui.pages

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.github.tscholze.duobahn.ui.components.map.MapOverlay
import com.github.tscholze.duobahn.ui.components.map.MapView
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import java.net.URL

/**
 * High level version of MapPage
 *
 * @param navController: App-wide navigation controller.
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MapPage(navController: NavController, repository: UnprocessedDataRepository = get()) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    //TODO the viewModel/repository might expose this as a flow, so we can collectAsState() here
    val autobahn by remember { mutableStateOf(repository.getAutobahns().first()) }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    var currentMarker by remember {
        mutableStateOf<MarkerDefinition?>(null)
    }

    MapPage(
        markers = autobahn.webcams + autobahn.roadworks,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        onFabClick = {
            coroutineScope.launch {
                with(bottomSheetScaffoldState.bottomSheetState) {
                    if (isCollapsed) expand() else collapse()
                }
            }
        },
        onMapClick = { currentMarker = it },
        currentMarker = currentMarker,
        openInMaps = { Toast.makeText(context, "TODO: open maps", Toast.LENGTH_SHORT).show()},
        openWeb = { Toast.makeText(context, "TODO: open web", Toast.LENGTH_SHORT).show()}
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
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    onFabClick: () -> Unit,
    onMapClick: (MarkerDefinition?) -> Unit,
    openInMaps: (MarkerDefinition) -> Unit,
    openWeb: (URL) -> Unit,
    currentMarker: MarkerDefinition?
) {
    // MARK: - Properties -

    // MARK: - Content -
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { SettingsPage() },
        sheetPeekHeight = 32.dp,
        floatingActionButton = {
            MapContentFloatingActionButton(onClick = onFabClick)
        }
    ) {
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

@ExperimentalMaterialApi
@Composable
private fun MapContentFloatingActionButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(R.string.map_fab_title)) },
        contentColor = Color.White,
        backgroundColor = AutobahnBlue,
        onClick = onClick,
        icon = {
            Icon(
                Icons.Default.Settings,
                contentDescription = stringResource(R.string.map_fab_title)
            )
        },
    )
}