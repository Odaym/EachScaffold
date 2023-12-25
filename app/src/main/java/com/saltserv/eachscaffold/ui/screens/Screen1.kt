package com.saltserv.eachscaffold.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Screen1(
    viewModel: Screen1ViewModel = viewModel(),
    navigateForward: (() -> Unit)? = null,
    navigateBack: () -> Unit
) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White,
//                    containerColor = Color.Black
//                ),
//                title = {
//                    Text(text = "Screen 1 - One")
//                },
//                navigationIcon = {
//                    IconButton(onClick = navigateBack) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = null
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = navigateForward,
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = null,
//                )
//            }
//        }
//    ) {
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
//    }
}
