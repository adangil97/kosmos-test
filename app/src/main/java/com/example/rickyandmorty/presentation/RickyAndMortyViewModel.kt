package com.example.rickyandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.domain.RickyAndMortyItem
import com.example.rickyandmorty.presentation.RickyAndMortyState.Error
import com.example.rickyandmorty.presentation.RickyAndMortyState.Loading
import com.example.rickyandmorty.presentation.RickyAndMortyState.Success
import com.example.rickyandmorty.usecases.GetRickyAndMortyCharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickyAndMortyViewModel @Inject constructor(
    private val getRickyAndMortyList: GetRickyAndMortyCharacterList
) : ViewModel() {
    private val mutableState: MutableStateFlow<RickyAndMortyState> =
        MutableStateFlow(Loading)
    val state: StateFlow<RickyAndMortyState> = mutableState.asStateFlow()

    fun getCharacterList() {
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
}