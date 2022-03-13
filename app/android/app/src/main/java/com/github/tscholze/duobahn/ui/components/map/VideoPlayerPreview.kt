package com.github.tscholze.duobahn.ui.components.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun VideoPlayerPreview(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    Surface(
        modifier = modifier.wrapContentSize(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentHeight()
        ) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_play),
                contentDescription = stringResource(R.string.open_web)
            )
        }
    }
}
