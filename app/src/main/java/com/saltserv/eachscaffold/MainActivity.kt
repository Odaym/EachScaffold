package com.saltserv.eachscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.saltserv.eachscaffold.ui.theme.EachScaffoldTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EachScaffoldTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    EachScaffoldApp()
                }
            }
        }
    }
}