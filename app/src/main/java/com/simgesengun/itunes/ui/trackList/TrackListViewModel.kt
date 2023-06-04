package com.simgesengun.itunes.ui.trackList

import androidx.lifecycle.viewModelScope
import com.simgesengun.itunes.R
import com.simgesengun.itunes.app.navigation.model.Screen
import com.simgesengun.itunes.data.datasource.remote.model.ResponseSearch
import com.simgesengun.itunes.domain.mapper.ErrorToResIdMapper
import com.simgesengun.itunes.domain.repo.ITunesRepo
import com.simgesengun.itunes.domain.usecase.GetRouteWithArgs
import com.simgesengun.itunes.network.model.NetworkResponse
import com.simgesengun.itunes.ui.BaseViewModel
import com.simgesengun.itunes.ui.components.alertDialog.AlertDialogUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class TrackListViewModel @Inject constructor(
    private val iTunesRepo: ITunesRepo,
    private val getRouteWithArgs: GetRouteWithArgs,
    private val errorToResIdMapper: ErrorToResIdMapper
) : BaseViewModel() {

    val trackListStateFlow = iTunesRepo.getTrackListAsFlow()
        .onStart {
            toggleProgressDialog(true)
        }
        .onEach {
            toggleProgressDialog(false)
            if(it.isEmpty()) {
                showErrorDialog()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(300L),
            initialValue = emptyList()
        )

    fun deleteById(id: Int) {
        viewModelScope.launch {
            iTunesRepo.deleteTrackById(id)
        }
    }

    fun navigateToDetail(id: Int) {
        navigateTo(
            getRouteWithArgs(
                GetRouteWithArgs.Params(
                    screen = Screen.TrackDetail,
                    argName = "id",
                    arg = id
                )
            )
        )
    }

    private fun showErrorDialog(
        response: NetworkResponse<ResponseSearch, Any>? = null
    ) {
        updateAlertDialog(
            alertDialogUIState = AlertDialogUIState.Custom(
                textResId = errorToResIdMapper.map(response),
                confirmButtonResId = R.string.try_again,
                dismissButtonResId = R.string.cancel,
                onConfirm = ::fetch
            )
        )
    }

    private fun fetch() {
        viewModelScope.launch {
            iTunesRepo.search().run {
                if(this !is NetworkResponse.Success) {
                    showErrorDialog(this)
                }
            }
        }
    }
}