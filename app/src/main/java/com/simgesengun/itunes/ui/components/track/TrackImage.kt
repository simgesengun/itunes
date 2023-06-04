package com.simgesengun.itunes.ui.components.track

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.simgesengun.itunes.ui.components.NetworkImage

@Composable
fun TrackImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    NetworkImage(
        imageUrl = imageUrl,
        contentDesc = "artworkUrl100",
        modifier = modifier.aspectRatio(1f),
        contentScale = ContentScale.FillWidth
    )
}