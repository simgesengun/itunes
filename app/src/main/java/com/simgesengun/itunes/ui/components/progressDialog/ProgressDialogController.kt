package com.simgesengun.itunes.ui.components.progressDialog

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
class ProgressDialogController @Inject constructor() {
    private val _isOpen = MutableStateFlow(false)
    val isOpen = _isOpen.asStateFlow()

    suspend fun toggle(isOpen: Boolean) {
        _isOpen.emit(isOpen)
    }
}