package com.droidcode.apps.wizarddb

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcode.apps.wizarddb.data.House
import kotlinx.coroutines.launch

class WizardsViewModel : ViewModel() {
    val houseState = mutableStateOf(emptyList<House>())

    init {
        viewModelScope.launch {
            getHouse()
        }
    }

    private suspend fun getHouse() {
        houseState.value = RetrofitClient.wizardDbAPIService.getHouse()
    }
}
