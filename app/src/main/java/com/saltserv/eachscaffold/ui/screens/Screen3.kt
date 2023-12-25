package com.saltserv.eachscaffold.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.saltserv.eachscaffold.ScaffoldViewState

@Composable
fun Screen3(
    scaffoldState: MutableState<ScaffoldViewState>,
    navigateForward: () -> Unit,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        scaffoldState.value = ScaffoldViewState(
            title = {
                Text(text = "Screen 3 - Three")
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
                    onClick = navigateForward,
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
        Text(text = "Screen 3 - Three")
    }
}
