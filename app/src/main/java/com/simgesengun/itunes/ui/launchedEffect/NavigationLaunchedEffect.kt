package com.simgesengun.itunes.ui.launchedEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.simgesengun.itunes.ui.BaseViewModel

@Composable
fun NavigationLaunchedEffect(
    navHostController: NavHostController,
    viewModel: BaseViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.navigateTo.collect { (navModel, popBackStack) ->
            if (popBackStack) navHostController.popBackStack()
            navModel?.let {
                navHostController.navigate(it)
            }
        }
    }
}