package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition
import com.github.tscholze.duobahn.data.domain.models.Roadwork
import com.github.tscholze.duobahn.data.domain.models.Webcam
import java.net.URL


/** Nullable auto-hiding variant of below */
@ExperimentalAnimationApi
@Composable
fun MapOverlay(
    marker: MarkerDefinition?,
    openInMaps: (MarkerDefinition) -> Unit,
    openWeb: (URL) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        targetState = marker,
        transitionSpec = { fadeIn() with fadeOut() },
        modifier = modifier.fillMaxWidth()
    ) { target ->
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 16.dp
        ) {
            when (target) {
                is Webcam -> WebcamOverlay(target, openInMaps, openWeb)
                is Roadwork -> RoadworksOverlay(roadwork = target)
                null -> Unit
                else -> throw NoWhenBranchMatchedException("Please add branch!")
            }
        }
    }
}

/**
 * Implements a markerOverlay as designed in figma:
 * https://www.figma.com/file/GX2jCbGtHolUftwzeTKgfN/DuoBahn?node-id=0%3A1
 */
@Composable
private fun WebcamOverlay(
    webcam: Webcam,
    openInMaps: (Webcam) -> Unit,
    openWeb: (URL) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = webcam.title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = webcam.direction,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        VideoPlayerPreview(
            imageUrl = webcam.thumbnailUrlString,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { webcam.linkURL?.let { openWeb(it) } },
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Button(onClick = { openInMaps(webcam) }) {
                Text(
                    text = stringResource(R.string.open_in_maps),
                    maxLines = 1,
                )
            }
            webcam.linkURL?.let {
                Button(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = { openWeb(it) }
                ) {
                    Text(
                        text = stringResource(R.string.open_web),
                        maxLines = 1,
                    )
                }
            }
        }

        //TODO: id seems to be empty/null most of the time?
        Text(
            text = stringResource(R.string.webcam_id, webcam.id),
            maxLines = 1,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun RoadworksOverlay(
    roadwork: Roadwork,
) {
    Text(
        text = "TODO: implement overlay for roadworks (${roadwork.title}",
        modifier = Modifier
            .padding(vertical = 12.dp)
            .padding(horizontal = 16.dp)
    )
}
