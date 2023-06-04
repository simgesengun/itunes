package com.simgesengun.itunes.ui.components.alertDialog

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class AlertDialogController @Inject constructor() {
    private val _uiState = MutableSharedFlow<AlertDialogUIState?>()
    val uiState = _uiState.asSharedFlow()

    suspend fun update(uiState: AlertDialogUIState?) {
        _uiState.emit(uiState)
    }
}