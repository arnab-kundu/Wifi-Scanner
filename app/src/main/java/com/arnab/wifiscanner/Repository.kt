package com.arnab.wifiscanner

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object Repository {
    private val _data = MutableStateFlow("WIFI: ")
    val data: StateFlow<String> get() = _data

    fun updateData(newData: String) {
        _data.value = newData
    }
}