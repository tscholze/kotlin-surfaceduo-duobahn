package com.github.tscholze.duobahn.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.ui.components.map.MapContent

/**
 * A map page contains the map itself and its controlling elements.
 */
@Composable
fun MapPage() {
    Box {
        // Z index: 0
        MapContent()

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