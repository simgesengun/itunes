package com.simgesengun.itunes.ui.trackDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.simgesengun.itunes.ui.components.toolbar.ToolbarUIState
import com.simgesengun.itunes.ui.components.track.ArtistName
import com.simgesengun.itunes.ui.components.track.ReleaseDateWithTitle
import com.simgesengun.itunes.ui.components.track.TrackImage
import com.simgesengun.itunes.ui.components.track.TrackName
import com.simgesengun.itunes.ui.components.track.TrackPrice
import com.simgesengun.itunes.ui.launchedEffect.LifecycleEffect
import com.simgesengun.itunes.ui.launchedEffect.NavigationLaunchedEffect
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun TrackDetailScreen(
    id: Int?,
    navHostController: NavHostController,
    viewModel: TrackDetailViewModel = hiltViewModel()
) {
    NavigationLaunchedEffect(
        navHostController = navHostController,
        viewModel = viewModel
    )
    LifecycleEffect(
        onStart = {
            viewModel.run {
                updateToolbar(toolbarUIState = ToolbarUIState.Detail)
                init(id)
            }
        },
        onStop = viewModel::clearScreen
    )

    val uiState by viewModel.uiState.collectAsState()

    uiState?.run {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.medium,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrackImage(
                artworkUrl100,
                modifier = Modifier.sizeIn(
                    maxWidth = 128.dp
                )
            )
            ArtistName(artistName)
            TrackName(trackName)
            collectionName?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center
                )
            }
            ReleaseDateWithTitle(releaseDate)
            TrackPrice(trackPrice)
        }
    }
}