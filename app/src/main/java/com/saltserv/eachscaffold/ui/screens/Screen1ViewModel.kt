package com.saltserv.eachscaffold.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Screen1ViewModel : ViewModel() {

    private val _data = MutableStateFlow<String?>(null)
    val data: StateFlow<String?> = _data.asStateFlow()

    fun getData(input: String) {
        _data.value = "$input ExtraParam"
        Log.d("MYDATA", "getData: What is the data? ${_data.value}")
    }
}