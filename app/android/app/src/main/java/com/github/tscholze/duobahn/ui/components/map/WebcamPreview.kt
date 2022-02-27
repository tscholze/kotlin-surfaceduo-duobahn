package com.github.tscholze.duobahn.ui.components.map


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WebcamPreview(){
    // TODO use real image
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .aspectRatio(2f)
            .background(Color.Gray)
    )
}