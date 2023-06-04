package com.simgesengun.itunes.ui.trackList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.simgesengun.itunes.ui.components.track.ErrorContent
import com.simgesengun.itunes.ui.components.track.LoadingContent
import com.simgesengun.itunes.ui.components.track.TrackRowCard
import com.simgesengun.itunes.ui.launchedEffect.LifecycleEffect
import com.simgesengun.itunes.ui.launchedEffect.NavigationLaunchedEffect
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun TrackListPaginationScreen(
    navHostController: NavHostController,
    viewModel: TrackListPaginationViewModel = hiltViewModel()
) {
    val tracks = viewModel.pagingDataStateFlow.collectAsLazyPagingItems()

    NavigationLaunchedEffect(
        navHostController = navHostController,
        viewModel = viewModel
    )
    LifecycleEffect(
        onStart =  viewModel::updateToolbar,
        onStop = viewModel::clearScreen
    )

    LazyColumn(
        modifier = Modifier.padding(
            horizontal = MaterialTheme.spacing.large
        )
    ) {
        items(
            count = tracks.itemCount,
            contentType = tracks.itemContentType()
        ) { index ->
            tracks[index]?.let {
                TrackRowCard(
                    uiState = it,
                    modifier = Modifier.padding(
                        vertical = MaterialTheme.spacing.small
                    ),
                    onClick = viewModel::navigateToDetail
                )
            }
        }

        when (tracks.loadState.refresh) {
            is LoadState.Error -> {
                item {
                    ErrorContent(
                        modifier = Modifier.fillMaxWidth(),
                        tryAgainOnClick = tracks::retry
                    )
                }
            }
            is LoadState.Loading -> {
                item {
                    LoadingContent(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            else -> {}
        }

        when (tracks.loadState.append) {
            is LoadState.Error -> {
                item {
                    ErrorContent(
                        modifier = Modifier.fillMaxWidth(),
                        tryAgainOnClick = tracks::retry
                    )
                }
            }
            is LoadState.Loading -> {
                item {
                    LoadingContent(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            else -> {}
        }
    }
}