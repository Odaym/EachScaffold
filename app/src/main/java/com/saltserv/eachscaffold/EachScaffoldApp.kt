package com.saltserv.eachscaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saltserv.eachscaffold.ui.screens.MainScreen
import com.saltserv.eachscaffold.ui.screens.ProfileScreen
import com.saltserv.eachscaffold.ui.screens.Screen1
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

    Scaffold(
        bottomBar = { EachScaffoldBottomBar(navController) },
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Routes.Main.path
        ) {
            composable(Routes.Main.path) {
                MainScreen(
                    navigateForward = { navController.navigate(Routes.Screen1.path) }
                )
            }
            composable(Routes.Profile.path) {
                ProfileScreen(
                    navigateForward = { navController.navigate(Routes.Screen1.path) }
                )
            }
            composable(Routes.Screen1.path) {
                Screen1(
                    navigateForward = {
                        navController.navigate(
                            Routes.Screen2.path.replace(
                                "{parameter}",
                                "My Extra Parameter"
                            )
                        )
                    },
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
                    navigateForward = { navController.navigate(Routes.Screen3.path) },
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(Routes.Screen3.path) {
                Screen3(
                    navigateForward = { navController.navigate(Routes.Screen4.path) },
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(Routes.Screen4.path) {
                Screen4(
                    navigateForward = {
                        navController.navigate(Routes.Main.path) {
                            popUpTo(Routes.Main.path) {
                            }
                            launchSingleTop = true
                        }
                    },
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}