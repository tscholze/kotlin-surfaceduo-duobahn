package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition


/** Nullable auto-hiding variant of below */
@ExperimentalAnimationApi
@Composable
fun MapOverlay(
    marker: MarkerDefinition?,
    modifier: Modifier
) {
    //TODO this animation looks weird
    AnimatedContent(
        targetState = marker,
        transitionSpec = { fadeIn() with fadeOut(animationSpec = tween(500))  },
        modifier = Modifier.fillMaxWidth()
    ) { markerTarget ->
        markerTarget?.let {
            MapOverlayImpl(markerTarget, modifier)
        }
    }
}

/**
 * Implements a markerOverlay as designed in figma:
 * https://www.figma.com/file/GX2jCbGtHolUftwzeTKgfN/DuoBahn?node-id=0%3A1
 */
@Composable
private fun MapOverlayImpl(
    marker: MarkerDefinition,
    modifier: Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "Selected a marker: ${marker.title}",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}