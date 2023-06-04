package com.simgesengun.itunes.app

import androidx.lifecycle.viewModelScope
import com.simgesengun.itunes.domain.repo.ITunesRepo
import com.simgesengun.itunes.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    itunesRepo: ITunesRepo
): BaseViewModel() {
    init {
        viewModelScope.launch {
            itunesRepo.search()
        }
    }
}