package com.saltserv.eachscaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saltserv.eachscaffold.ui.getAppBarTitle
import com.saltserv.eachscaffold.ui.getFloatingActionButton
import com.saltserv.eachscaffold.ui.getNavigationIcon
import com.saltserv.eachscaffold.ui.screens.MainScreen
import com.saltserv.eachscaffold.ui.screens.ProfileScreen
import com.saltserv.eachscaffold.ui.screens.Screen1
import com.saltserv.eachscaffold.ui.screens.Screen2
import com.saltserv.eachscaffold.ui.screens.Screen3
import com.saltserv.eachscaffold.ui.screens.Screen4

data class ScaffoldViewState(
    val title: @Composable () -> Unit = {},
    val navigationIcon: @Composable () -> Unit = {},
    val floatingActionButton: @Composable () -> Unit = {}
)

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

    val scaffoldState = remember {
        mutableStateOf(ScaffoldViewState())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    containerColor = Color.Black
                ),
                title = navBackStackEntry?.getAppBarTitle(scaffoldState) ?: {},
                navigationIcon = navBackStackEntry?.getNavigationIcon(navController) ?: {}
            )
        },
        floatingActionButton = navBackStackEntry?.getFloatingActionButton(
            scaffoldState = scaffoldState,
            navController = navController
        ) ?: {},
        bottomBar = { EachScaffoldBottomBar(navController) },
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Routes.Main.path
        ) {
            composable(Routes.Main.path) {
                MainScreen(
                )
            }
            composable(Routes.Profile.path) {
                ProfileScreen()
            }
            composable(Routes.Screen1.path) {
                Screen1(
                    scaffoldState = scaffoldState,
                    navigateForward = { myData ->
                        navController.navigate(
                            Routes.Screen2.path.replace(
                                "{parameter}",
                                myData
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
                    scaffoldState = scaffoldState,
                    extraParameter = parameter,
                    navigateForward = { navController.navigate(Routes.Screen3.path) },
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(Routes.Screen3.path) {
                Screen3(
                    scaffoldState = scaffoldState,
                    navigateForward = {
                        navController.navigate(Routes.Screen4.path)
                    },
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(Routes.Screen4.path) {
                Screen4(
                    scaffoldState = scaffoldState,
                    navigateForward = {
                        navController.navigate(Routes.Main.path) {
                            popUpTo(Routes.Main.path)
                            launchSingleTop = true
                        }
                    },
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}