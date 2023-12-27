package com.saltserv.eachscaffold.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.saltserv.eachscaffold.R
import com.saltserv.eachscaffold.Routes
import com.saltserv.eachscaffold.ScaffoldViewState

@Composable
fun NavBackStackEntry.getAppBarTitle(
    scaffoldState: MutableState<ScaffoldViewState>
) = if (isBottomRoute()) {
    {
        scaffoldState.value = ScaffoldViewState()
        Text(text = getBottomRouteTitle())
    }
} else {
    scaffoldState.value.title
}

@Composable
fun NavBackStackEntry.getBottomRouteTitle() = when (destination.route) {
    Routes.Main.path -> {
        Routes.Main.path
    }

    Routes.Profile.path -> {
        Routes.Profile.path
    }

    else -> stringResource(id = R.string.app_name)
}

fun NavBackStackEntry.isBottomRoute() = when (destination.route) {
    Routes.Main.path,
    Routes.Profile.path -> true

    else -> false
}

@Composable
fun NavBackStackEntry.getNavigationIcon(
    navController: NavHostController
): @Composable () -> Unit {
    if (isBottomRoute()) {
        return {}
    }

    val onClickAction: () -> Unit = when (destination.route) {
        Routes.Screen1.path,
        Routes.Screen2.path -> {
            { navController.navigateUp() }
        }

        else -> {
            {}
        }
    }

    return {
        IconButton(onClick = onClickAction) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }
}

@Composable
fun NavBackStackEntry.getFloatingActionButton(
    scaffoldState: MutableState<ScaffoldViewState>,
    navController: NavHostController
): @Composable () -> Unit = if (isBottomRoute()) {
    {
        when (destination.route) {
            Routes.Main.path,
            Routes.Profile.path -> {
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.Screen1.path) },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                    )
                }

            }
        }
    }
} else {
    scaffoldState.value.floatingActionButton
}
