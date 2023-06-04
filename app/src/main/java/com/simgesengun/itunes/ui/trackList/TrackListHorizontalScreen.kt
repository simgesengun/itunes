package com.simgesengun.itunes.ui.trackList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.simgesengun.itunes.ui.components.track.TrackColumnCard
import com.simgesengun.itunes.ui.launchedEffect.LifecycleEffect
import com.simgesengun.itunes.ui.launchedEffect.NavigationLaunchedEffect
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun TrackListHorizontalScreen(
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

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        items(trackList) { track ->
            TrackColumnCard(
                uiState = track,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium
                ),
                onClick = viewModel::navigateToDetail,
                deleteOnClick = viewModel::deleteById
            )
        }
    }
}