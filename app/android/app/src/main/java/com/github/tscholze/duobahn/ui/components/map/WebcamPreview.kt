package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.tscholze.duobahn.R
import com.github.tscholze.duobahn.data.domain.models.Webcam

@Composable
fun WebcamPreview(
    modifier: Modifier = Modifier,
    webcam: Webcam,
) {
    Surface(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = rememberImagePainter(webcam.thumbnailUrlString),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            )

            Image(
                painter = painterResource(R.drawable.ic_play),
                contentDescription = stringResource(R.string.open_web)
            )
        }
    }
}
