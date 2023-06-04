package com.simgesengun.itunes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simgesengun.itunes.ui.components.alertDialog.AlertDialogController
import com.simgesengun.itunes.ui.components.alertDialog.AlertDialogUIState
import com.simgesengun.itunes.ui.components.progressDialog.ProgressDialogController
import com.simgesengun.itunes.ui.components.toolbar.ToolbarController
import com.simgesengun.itunes.ui.components.toolbar.ToolbarUIState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var progressDialogController: ProgressDialogController

    @Inject
    lateinit var alertDialogController: AlertDialogController

    @Inject
    lateinit var toolbarController: ToolbarController

    private val _navigateTo: MutableSharedFlow<Pair<String?, Boolean>> = MutableSharedFlow()
    val navigateTo = _navigateTo.asSharedFlow()

    fun clearScreen() {
        toggleProgressDialog(false)
        updateAlertDialog(null)
    }

    fun updateToolbar(toolbarUIState: ToolbarUIState? = ToolbarUIState.Main) {
        viewModelScope.launch {
            toolbarController.updateToolbarUIState(toolbarUIState)
        }
    }

    protected fun navigateTo(route: String, popBackStack: Boolean = false) {
        viewModelScope.launch {
            _navigateTo.emit(route to popBackStack)
        }
    }

    protected fun popBackStack() {
        viewModelScope.launch {
            _navigateTo.emit(null to true)
        }
    }

    protected fun updateAlertDialog(alertDialogUIState: AlertDialogUIState? = null) {
        viewModelScope.launch {
            alertDialogController.update(alertDialogUIState)
        }
    }

    protected fun toggleProgressDialog(isOpen: Boolean) {
        viewModelScope.launch {
            progressDialogController.toggle(isOpen)
        }
    }
}