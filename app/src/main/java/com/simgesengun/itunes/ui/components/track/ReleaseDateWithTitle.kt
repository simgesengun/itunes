package com.simgesengun.itunes.ui.components.track

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun ReleaseDateWithTitle(releaseDate: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(id = R.string.release_date),
            style = MaterialTheme.typography.titleSmall
        )
        Text(releaseDate)
    }
}