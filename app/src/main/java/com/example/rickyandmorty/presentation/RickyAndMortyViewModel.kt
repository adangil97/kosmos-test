package com.example.rickyandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickyandmorty.domain.RickyAndMortyItem
import com.example.rickyandmorty.framework.getRickyAndMortyList
import com.example.rickyandmorty.presentation.RickyAndMortyState.Error
import com.example.rickyandmorty.presentation.RickyAndMortyState.Loading
import com.example.rickyandmorty.presentation.RickyAndMortyState.Success
import com.example.rickyandmorty.usecases.GetRickyAndMortyList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RickyAndMortyViewModel(
    private val getRickyAndMortyList: GetRickyAndMortyList
) : ViewModel() {
    private val mutableState: MutableStateFlow<RickyAndMortyState> =
        MutableStateFlow(Loading)
    val state: StateFlow<RickyAndMortyState> = mutableState.asStateFlow()

    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.value = Loading
            val results = getRickyAndMortyList()
            if (results.isNotEmpty()) {
                mutableState.value = Success(results.map(RickyAndMortyItem::toRickyAndMortyUiModel))
            } else {
                mutableState.value = Error("Ocurrió un error desconocido")
            }
        }
    }

    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val getRickyAndMortyList = getRickyAndMortyList()
                RickyAndMortyViewModel(getRickyAndMortyList)
            }
        }
    }
}