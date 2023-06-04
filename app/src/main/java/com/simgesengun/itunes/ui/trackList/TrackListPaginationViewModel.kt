package com.simgesengun.itunes.ui.trackList

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.simgesengun.itunes.app.navigation.model.Screen
import com.simgesengun.itunes.domain.repo.ITunesRepo
import com.simgesengun.itunes.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackListPaginationViewModel @Inject constructor(
    iTunesRepo: ITunesRepo
) : BaseViewModel() {

    val pagingDataStateFlow = iTunesRepo.getTrackUIStatePagingData()
        .cachedIn(viewModelScope)

    fun navigateToDetail(id: Int) {
        navigateTo(
            Screen.TrackDetail.route.replace(
                "{id}", id.toString()
            )
        )
    }
}