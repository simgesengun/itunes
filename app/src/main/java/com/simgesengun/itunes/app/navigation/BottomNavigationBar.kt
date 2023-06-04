package com.simgesengun.itunes.app.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import com.simgesengun.itunes.app.navigation.model.Screen
import com.simgesengun.itunes.app.navigation.model.Screen.BottomNavigationScreen.Companion.isBottomBarEnabled

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController,
    items: List<Screen.BottomNavigationScreen>
) {
    val currentRoute = navHostController.currentRoute()
    if(currentRoute.isBottomBarEnabled()) {
        NavigationBar {
            items.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(screen.iconResId),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = screen.labelResId)
                        )
                    },
                    selected = currentRoute == screen.route,
                    alwaysShowLabel = true,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navHostController.navigate(
                                route = screen.route,
                                navOptions = NavOptions.Builder()
                                    .setPopUpTo(0, false)
                                    .setLaunchSingleTop(true)
                                    .build()
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun NavHostController.currentRoute(): String? {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}