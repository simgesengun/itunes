package com.simgesengun.itunes.app.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.simgesengun.itunes.R

sealed class Screen(
    open val route: String
) {
    object TrackDetail : Screen("TrackDetail/{id}")

    sealed class BottomNavigationScreen(
        override val route: String,
        @StringRes val labelResId: Int,
        @DrawableRes val iconResId: Int
    ) : Screen(
        route = route
    ) {
        object TrackListPagination : BottomNavigationScreen(
            "trackListPagination",
            R.string.bottom_bar_list_pagination,
            R.drawable.baseline_format_list_numbered_24
        )

        object TrackListVertical : BottomNavigationScreen(
            "trackListVertical",
            R.string.bottom_bar_list_vertical,
            R.drawable.outline_swap_vertical_circle_24
        )

        object TrackListGrid : BottomNavigationScreen(
            "trackListGrid",
            R.string.bottom_bar_list_grid,
            R.drawable.baseline_grid_on_24
        )

        object TrackListHorizontal : BottomNavigationScreen(
            "trackListHorizontal",
            R.string.bottom_bar_list_horizontal,
            R.drawable.outline_swap_horizontal_circle_24
        )

        companion object {
            fun getBottomBarList() =
                listOf(TrackListPagination, TrackListVertical, TrackListGrid, TrackListHorizontal)
            fun String?.isBottomBarEnabled() = getBottomBarList().any { it.route == this }
        }
    }
}