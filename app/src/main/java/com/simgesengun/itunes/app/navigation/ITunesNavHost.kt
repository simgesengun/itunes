package com.simgesengun.itunes.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.simgesengun.itunes.app.navigation.model.Screen.BottomNavigationScreen.TrackListGrid
import com.simgesengun.itunes.app.navigation.model.Screen.BottomNavigationScreen.TrackListHorizontal
import com.simgesengun.itunes.app.navigation.model.Screen.BottomNavigationScreen.TrackListPagination
import com.simgesengun.itunes.app.navigation.model.Screen.BottomNavigationScreen.TrackListVertical
import com.simgesengun.itunes.app.navigation.model.Screen.TrackDetail
import com.simgesengun.itunes.ui.trackDetail.TrackDetailScreen
import com.simgesengun.itunes.ui.trackList.TrackListGridScreen
import com.simgesengun.itunes.ui.trackList.TrackListHorizontalScreen
import com.simgesengun.itunes.ui.trackList.TrackListPaginationScreen
import com.simgesengun.itunes.ui.trackList.TrackListVerticalScreen

@Composable
fun ITunesNavHost(
    navHostController: NavHostController
) {
    NavHost(navHostController, startDestination = TrackListPagination.route) {
        composable(TrackListPagination.route) {
            TrackListPaginationScreen(navHostController)
        }
        composable(TrackListVertical.route) {
            TrackListVerticalScreen(navHostController)
        }
        composable(TrackListGrid.route) {
            TrackListGridScreen(navHostController)
        }
        composable(TrackListHorizontal.route) {
            TrackListHorizontalScreen(navHostController)
        }
        composable(
            TrackDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            TrackDetailScreen(it.arguments?.getInt("id"), navHostController)
        }
    }
}