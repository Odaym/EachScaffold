package com.saltserv.eachscaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun EachScaffoldBottomBar(
    navController: NavHostController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text(text = Routes.Main.path) },
            selected = currentDestination?.hierarchy?.any { dest ->
                dest.route == Routes.Main.path
            } == true,
            onClick = { navController.handleNavigation(Routes.Main.path) },
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            label = { Text(text = Routes.Profile.path) },
            selected = currentDestination?.hierarchy?.any { dest ->
                dest.route == Routes.Profile.path
            } == true,
            onClick = { navController.handleNavigation(Routes.Profile.path) },
        )
    }
}

fun NavHostController.handleNavigation(
    route: String
) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}

