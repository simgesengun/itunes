package com.simgesengun.itunes.ui.trackDetail

import androidx.lifecycle.viewModelScope
import com.simgesengun.itunes.R
import com.simgesengun.itunes.domain.repo.ITunesRepo
import com.simgesengun.itunes.ui.BaseViewModel
import com.simgesengun.itunes.ui.components.alertDialog.AlertDialogUIState
import com.simgesengun.itunes.ui.uiState.TrackUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val iTunesRepo: ITunesRepo
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<TrackUIState?>(null)
    val uiState = _uiState.asStateFlow()

    fun init(id: Int?) {
        when(id) {
            null -> showNotFoundDialog()
            else -> getTrackById(id)
        }
    }

    private fun getTrackById(id: Int) {
        viewModelScope.launch {
            iTunesRepo.getTrackById(id).onStart {
                toggleProgressDialog(true)
            }.collectLatest {
                toggleProgressDialog(false)
                when(it) {
                    null -> showNotFoundDialog()
                    else -> _uiState.emit(it)
                }
            }
        }
    }

    private fun showNotFoundDialog() {
        updateAlertDialog(
            alertDialogUIState = AlertDialogUIState.Default(
                textResId = R.string.not_found,
                onConfirm = ::popBackStack
            )
        )
    }
}