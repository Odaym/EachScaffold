package com.saltserv.eachscaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saltserv.eachscaffold.ui.screens.MainScreen
import com.saltserv.eachscaffold.ui.screens.ProfileScreen
import com.saltserv.eachscaffold.ui.screens.Screen1
import com.saltserv.eachscaffold.ui.screens.Screen1ViewModel
import com.saltserv.eachscaffold.ui.screens.Screen2
import com.saltserv.eachscaffold.ui.screens.Screen3
import com.saltserv.eachscaffold.ui.screens.Screen4

sealed class Routes(
    val path: String
) {
    data object Main : Routes("Main")
    data object Profile : Routes("Profile")

    data object Screen1 : Routes("Screen1")
    data object Screen2 : Routes("Screen2/{parameter}")
    data object Screen3 : Routes("Screen3")
    data object Screen4 : Routes("Screen4")
}

@ExperimentalMaterial3Api
@Composable
fun EachScaffoldApp() {

    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    containerColor = Color.Black
                ),
                title = {
                    Text(
                        text = getScreenTitle(navBackStackEntry)
                            ?: "Each Scaffold App"
                    )
                },
                navigationIcon = {
                    GetPreviousDestination(navController, navBackStackEntry)
                }
            )
        },
        floatingActionButton = {
            val screen1ViewModel: Screen1ViewModel = viewModel()

            val myData = screen1ViewModel.data.collectAsState()

            FloatingActionButton(
                onClick = {
                    getNextDestination(
                        myData.value,
                        navController,
                        navBackStackEntry
                    )
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                )
            }
        },
        bottomBar = { EachScaffoldBottomBar(navController) },
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Routes.Main.path
        ) {
            composable(Routes.Main.path) {
                MainScreen()
            }
            composable(Routes.Profile.path) {
                ProfileScreen()
            }
            composable(Routes.Screen1.path) {
                Screen1(
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(
                Routes.Screen2.path,
                arguments = listOf(navArgument("parameter") { type = NavType.StringType })
            ) { extra ->

                val parameter = extra.arguments?.getString("parameter") ?: ""

                Screen2(
                    extraParameter = parameter,
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(Routes.Screen3.path) {
                Screen3(
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(Routes.Screen4.path) {
                Screen4(
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}

fun getScreenTitle(
    navBackStackEntry: NavBackStackEntry?
) = when (navBackStackEntry?.destination?.route) {
    Routes.Main.path,
    Routes.Profile.path,
    Routes.Screen1.path,
    Routes.Screen2.path,
    Routes.Screen3.path,
    Routes.Screen4.path -> {
        navBackStackEntry.destination.route
    }

    else -> null
}

fun getNextDestination(
    myData: String?,
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
) = when (navBackStackEntry?.destination?.route) {

    // Both go to Screen1
    Routes.Profile.path,
    Routes.Main.path -> {
        navController.navigate(Routes.Screen1.path)
    }

    Routes.Screen1.path -> {
        navController.navigate(
            Routes.Screen2.path.replace(
                "{parameter}",
                "Extra parameter"
            )
        )
    }

    Routes.Screen2.path -> {
        navController.navigate(Routes.Screen3.path)
    }

    Routes.Screen3.path -> {
        navController.navigate(Routes.Screen4.path)
    }

    Routes.Screen4.path -> {
        navController.navigate(Routes.Main.path) {
            popUpTo(Routes.Main.path) {
            }
            launchSingleTop = true
        }
    }

    else -> {}
}

@Composable
fun GetPreviousDestination(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
) = when (navBackStackEntry?.destination?.route) {

    Routes.Screen1.path,
    Routes.Screen2.path,
    Routes.Screen3.path,
    Routes.Screen4.path -> {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }

    else -> {}
}