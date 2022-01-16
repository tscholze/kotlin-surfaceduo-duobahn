package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.toMarkerDefinition
import com.github.tscholze.duobahn.data.network.repositories.UnprocessedDataRepository
import com.github.tscholze.duobahn.ui.components.map.MapView
import com.github.tscholze.duobahn.ui.theme.AutobahnBlue
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get


/**
 * A map page contains the map itself and its controlling elements.
 *
 * @param navController: App-wide navigation controller.
 */
@Composable
@ExperimentalMaterialApi
fun MapPage(navController: NavController, repository: UnprocessedDataRepository = get()) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { SettingsPage() },
        sheetPeekHeight = 32.dp,
        floatingActionButton = {
            MapContentFloatingActionButton(bottomSheetScaffoldState)
        }
    ) {
        Box {
            // Z index: 0
            MapView(
                markers = repository.getAutobahns()
                    .first()
                    .let { autobahn ->
                        autobahn.webcams.map { it.toMarkerDefinition() } +
                                autobahn.roadworks.map { it.toMarkerDefinition() }
                    }
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

@ExperimentalMaterialApi
@Composable
private fun MapContentFloatingActionButton(
    state: BottomSheetScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(R.string.map_fab_title)) },
        contentColor = Color.White,
        backgroundColor = AutobahnBlue,
        onClick = {
            val bottomSheetState = state.bottomSheetState
            coroutineScope.launch {
                if (bottomSheetState.isCollapsed) {
                    bottomSheetState.expand()
                } else {
                    bottomSheetState.collapse()
                }
            }
        },
        icon = {
            Icon(
                Icons.Default.Settings,
                contentDescription = stringResource(R.string.map_fab_title)
            )
        },
    )
}