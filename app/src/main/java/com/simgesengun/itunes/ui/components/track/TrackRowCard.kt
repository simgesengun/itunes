package com.simgesengun.itunes.ui.components.track

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.simgesengun.itunes.ui.components.ArrowBox
import com.simgesengun.itunes.ui.theme.ITunesTheme
import com.simgesengun.itunes.ui.theme.spacing
import com.simgesengun.itunes.ui.uiState.TrackUIState

@Composable
fun TrackRowCard(
    uiState: TrackUIState,
    modifier: Modifier = Modifier,
    showImage: Boolean = true,
    onClick: (Int) -> Unit
) {
    uiState.run {
        Card(
            modifier = modifier
                .clip(MaterialTheme.shapes.medium)
                .clickable {
                    onClick.invoke(trackId)
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showImage) TrackImage(
                    imageUrl = artworkUrl100,
                    modifier = Modifier
                    .fillMaxHeight()
                    .sizeIn(
                        maxWidth = 64.dp,
                        maxHeight = 64.dp
                    )
                )
                Column(modifier = Modifier.weight(1f)) {
                    ArtistName(artistName, 1)
                    TrackName(trackName, 1)
                }
                ArrowBox(
                    modifier = Modifier.fillMaxHeight(0.75f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(TrackUIStatePreviewProvider::class) uiState: TrackUIState
) {
    ITunesTheme {
        TrackRowCard(
            uiState = uiState,
            onClick = { }
        )
    }
}