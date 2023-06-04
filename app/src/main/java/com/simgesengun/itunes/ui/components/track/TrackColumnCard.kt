package com.simgesengun.itunes.ui.components.track

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.simgesengun.itunes.ui.components.ArrowBox
import com.simgesengun.itunes.ui.components.DeleteButton
import com.simgesengun.itunes.ui.theme.spacing
import com.simgesengun.itunes.ui.uiState.TrackUIState

@Composable
fun TrackColumnCard(
    uiState: TrackUIState,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    deleteOnClick: (Int) -> Unit
) {
    uiState.run {
        Card(
            modifier = modifier
                .width(140.dp)
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    onClick.invoke(trackId)
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    space = MaterialTheme.spacing.medium
                )
            ) {
                DeleteButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        deleteOnClick.invoke(trackId)
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.large),
                    verticalArrangement = Arrangement.spacedBy(
                        space = MaterialTheme.spacing.small
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TrackImage(artworkUrl100)
                    ArtistName(artistName, 1)
                    TrackName(trackName, 1)
                    Text(releaseDate)
                    TrackPrice(trackPrice)
                }
                ArrowBox(
                    modifier = Modifier
                        .align(Alignment.End)
                        .fillMaxWidth(0.3f)
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
    TrackColumnCard(
        uiState = uiState,
        onClick = { },
        deleteOnClick = { }
    )
}