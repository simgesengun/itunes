package com.simgesengun.itunes.ui.components.track

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TrackName(
    trackName: String,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = trackName,
        style = MaterialTheme.typography.titleMedium,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}