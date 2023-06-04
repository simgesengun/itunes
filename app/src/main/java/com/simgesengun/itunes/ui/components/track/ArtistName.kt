package com.simgesengun.itunes.ui.components.track

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ArtistName(
    artistName: String,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = artistName,
        style = MaterialTheme.typography.titleLarge,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}