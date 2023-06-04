package com.simgesengun.itunes.ui.trackList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.simgesengun.itunes.ui.components.track.TrackRowCard
import com.simgesengun.itunes.ui.launchedEffect.LifecycleEffect
import com.simgesengun.itunes.ui.launchedEffect.NavigationLaunchedEffect
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun TrackListGridScreen(
    navHostController: NavHostController,
    viewModel: TrackListViewModel = hiltViewModel()
) {
    val trackList by viewModel.trackListStateFlow.collectAsState()

    NavigationLaunchedEffect(
        navHostController = navHostController,
        viewModel = viewModel
    )
    LifecycleEffect(
        onStart =  viewModel::updateToolbar,
        onStop = viewModel::clearScreen
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(
            horizontal = MaterialTheme.spacing.large
        ),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        items(trackList) { track ->
            TrackRowCard(
                uiState = track,
                showImage = false,
                onClick = viewModel::navigateToDetail
            )
        }
    }
}