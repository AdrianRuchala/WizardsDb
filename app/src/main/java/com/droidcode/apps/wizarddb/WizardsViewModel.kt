package com.droidcode.apps.wizarddb

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcode.apps.wizarddb.data.Elixir
import com.droidcode.apps.wizarddb.data.House
import com.droidcode.apps.wizarddb.data.Spell
import kotlinx.coroutines.launch

class WizardsViewModel : ViewModel() {
    val houseState = mutableStateOf(emptyList<House>())
    val spellState = mutableStateOf(emptyList<Spell>())
    val elixirState = mutableStateOf(emptyList<Elixir>())

    init {
        viewModelScope.launch {
            getHouses()
            getSpells()
            getElixirs()
        }
    }

    private suspend fun getHouses() {
        houseState.value = RetrofitClient.wizardDbAPIService.getHouses()
    }

    private suspend fun getSpells() {
        spellState.value = RetrofitClient.wizardDbAPIService.getSpells()
    }

    private suspend fun getElixirs() {
        elixirState.value = RetrofitClient.wizardDbAPIService.getElixirs()
    }
}
