package com.simgesengun.itunes.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.simgesengun.itunes.app.navigation.BottomNavigationBar
import com.simgesengun.itunes.app.navigation.ITunesNavHost
import com.simgesengun.itunes.app.navigation.model.Screen.BottomNavigationScreen.Companion.getBottomBarList
import com.simgesengun.itunes.ui.components.alertDialog.ITunesAlertDialog
import com.simgesengun.itunes.ui.components.progressDialog.ITunesProgressDialog
import com.simgesengun.itunes.ui.components.toolbar.Toolbar
import com.simgesengun.itunes.ui.theme.ITunesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainActivityViewModel>()

        setContent {
            val alertDialogUIState by viewModel.alertDialogController.uiState.collectAsState(null)
            val progressDialogIsOpen by viewModel.progressDialogController.isOpen.collectAsState()
            val toolbarUIState by viewModel.toolbarController.uiState.collectAsState()
            val navHostController = rememberNavController()

            ITunesTheme {
                Scaffold(
                    topBar = {
                        Toolbar(
                            toolbarUIState = toolbarUIState,
                            onBackButtonClickListener = onBackPressedDispatcher::onBackPressed
                        )
                    },
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            ITunesAlertDialog(
                                uiState = alertDialogUIState
                            )
                            ITunesProgressDialog(isOpen = progressDialogIsOpen)
                            ITunesNavHost(
                                navHostController = navHostController
                            )
                        }
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            navHostController = navHostController,
                            items = getBottomBarList()
                        )
                    }
                )
            }
        }
    }
}