package com.simgesengun.itunes.ui.trackList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.components.track.TrackRowCard
import com.simgesengun.itunes.ui.launchedEffect.LifecycleEffect
import com.simgesengun.itunes.ui.launchedEffect.NavigationLaunchedEffect
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun TrackListVerticalScreen(
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

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.large
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = pluralStringResource(id = R.plurals.results, trackList.size, trackList.size),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        items(trackList) { track ->
            TrackRowCard(
                uiState = track,
                showImage = false,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.small
                ),
                onClick = viewModel::navigateToDetail
            )
        }
    }
}