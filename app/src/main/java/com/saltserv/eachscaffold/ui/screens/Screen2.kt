package com.saltserv.eachscaffold.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Screen2(
    extraParameter: String,
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
//                    Text(text = "Screen 2 - Two - Extra $extraParameter")
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
        Text(text = "Screen 2 - Two")
    }
//    }
}
