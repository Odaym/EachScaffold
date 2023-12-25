package com.saltserv.eachscaffold.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltserv.eachscaffold.ScaffoldViewState

@Composable
fun Screen1(
    viewModel: Screen1ViewModel = viewModel(),
    scaffoldState: MutableState<ScaffoldViewState>,
    navigateForward: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val myData = viewModel.data.collectAsState()

    LaunchedEffect(Unit) {
        scaffoldState.value = ScaffoldViewState(
            title = {
                Text(text = "Screen 1 - One")
            },
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigateForward(myData.value ?: "") },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                    )
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .clickable {
                    viewModel.getData("${2 * 7}")
                },
            text = "Screen 1 - One"
        )
    }
}
