package com.simgesengun.itunes.ui.components.alertDialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.window.DialogProperties
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.components.ITunesButton
import com.simgesengun.itunes.ui.theme.ITunesTheme

@Composable
fun ITunesAlertDialog(
    uiState: AlertDialogUIState?,
    dismissOnClickOutside: Boolean = false
) {
    val isOpen = remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState != null) isOpen.value = true
    }

    val closeDialog = {
        uiState?.onDismiss?.invoke()
        isOpen.value = false
    }

    if (isOpen.value) {
        uiState?.run {
            AlertDialog(
                text = {
                    textResId?.let {
                        Text(text = stringResource(id = it))
                    }
                },
                title = {
                    titleResId?.let {
                        Text(text = stringResource(id = it))
                    }
                },
                onDismissRequest = closeDialog,
                confirmButton = {
                    confirmButtonResId?.let {
                        ITunesButton(
                            textResId = it,
                            onClick = {
                                isOpen.value = false
                                onConfirm()
                            }
                        )
                    }
                },
                dismissButton = {
                    dismissButtonResId?.let {
                        ITunesButton(
                            textResId = it,
                            onClick = closeDialog
                        )
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = dismissOnClickOutside,
                    dismissOnClickOutside = dismissOnClickOutside
                )
            )
        }
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(AlertDialogPreviewProvider::class) uiState: AlertDialogUIState
) {
    ITunesTheme {
        ITunesAlertDialog(uiState = uiState)
    }
}

class AlertDialogPreviewProvider : PreviewParameterProvider<AlertDialogUIState> {
    override val values = sequenceOf(
        AlertDialogUIState.Default(
            textResId = R.string.okay,
            titleResId = R.string.okay
        ),
        AlertDialogUIState.Custom(
            textResId = R.string.okay,
            titleResId = R.string.okay,
            confirmButtonResId = R.string.okay,
            dismissButtonResId = R.string.okay
        )
    )
}