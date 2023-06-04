package com.simgesengun.itunes.ui.components.track

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.components.ITunesButton
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    tryAgainOnClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(
            vertical = MaterialTheme.spacing.small
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        Text(
            stringResource(id = R.string.error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        ITunesButton(onClick = tryAgainOnClick, textResId = R.string.try_again)
    }
}