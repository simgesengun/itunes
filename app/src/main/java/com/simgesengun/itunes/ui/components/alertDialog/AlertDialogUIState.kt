package com.simgesengun.itunes.ui.components.alertDialog

import androidx.annotation.StringRes
import com.simgesengun.itunes.R
import java.util.UUID

sealed class AlertDialogUIState(
    @StringRes open val textResId: Int? = null,
    @StringRes open val titleResId: Int? = null,
    @StringRes open val confirmButtonResId: Int? = null,
    @StringRes open val dismissButtonResId: Int? = null,
    open val onConfirm: () -> Unit = { },
    open val onDismiss: () -> Unit = { }
) {
    data class Default(
        val id: UUID = UUID.randomUUID(),
        @StringRes override val textResId: Int?,
        @StringRes override val titleResId: Int? = null,
        override val onConfirm: () -> Unit = { }
    ) : AlertDialogUIState(
        textResId = textResId,
        titleResId = titleResId,
        confirmButtonResId = R.string.okay,
        onConfirm = onConfirm
    )

    data class Custom(
        val id: UUID = UUID.randomUUID(),
        @StringRes override val textResId: Int?,
        @StringRes override val titleResId: Int? = null,
        @StringRes override val confirmButtonResId: Int? = null,
        @StringRes override val dismissButtonResId: Int? = null,
        override val onConfirm: () -> Unit = { },
        override val onDismiss: () -> Unit = { }
    ) : AlertDialogUIState(
        textResId = textResId,
        titleResId = titleResId,
        confirmButtonResId = confirmButtonResId,
        dismissButtonResId = dismissButtonResId,
        onConfirm = onConfirm,
        onDismiss = onDismiss
    )
}