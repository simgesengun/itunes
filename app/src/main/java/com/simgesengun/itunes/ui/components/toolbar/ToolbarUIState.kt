package com.simgesengun.itunes.ui.components.toolbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.simgesengun.itunes.R

sealed class ToolbarUIState(
    @StringRes val title: Int,
    val isBackButtonEnabled: Boolean,
    @DrawableRes val imageResId: Int? = null
) {
    object Main : ToolbarUIState(
        title = R.string.name_surname,
        isBackButtonEnabled = false,
        imageResId = R.drawable.ic_woman_music
    )

    object Detail : ToolbarUIState(
        title = R.string.detail,
        isBackButtonEnabled = true
    )
}