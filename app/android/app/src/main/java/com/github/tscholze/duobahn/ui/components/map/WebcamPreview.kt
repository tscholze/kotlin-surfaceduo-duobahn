package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.Webcam
    
@Composable
fun WebcamPreview(
    modifier: Modifier = Modifier,
    webcam: Webcam,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center){
            // TODO use real image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .background(Color.Gray)
            )

            Image(
                painter = painterResource(R.drawable.ic_play),
                contentDescription = stringResource(R.string.open_web)
            )
        }
    }
}