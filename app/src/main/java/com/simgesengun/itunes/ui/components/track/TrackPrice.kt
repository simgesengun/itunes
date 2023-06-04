package com.simgesengun.itunes.ui.components.track

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.simgesengun.itunes.R

@Composable
fun TrackPrice(
    trackPrice: String?
) {
    Text(
        text = trackPrice ?: stringResource(id = R.string.undefined),
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}