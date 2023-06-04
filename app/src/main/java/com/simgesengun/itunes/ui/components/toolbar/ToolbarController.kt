package com.simgesengun.itunes.ui.components.toolbar

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
class ToolbarController @Inject constructor() {
    private val _uiState = MutableStateFlow<ToolbarUIState?>(null)
    val uiState = _uiState.asStateFlow()

    suspend fun updateToolbarUIState(toolbarUIState: ToolbarUIState?) {
        _uiState.emit(toolbarUIState)
    }
}