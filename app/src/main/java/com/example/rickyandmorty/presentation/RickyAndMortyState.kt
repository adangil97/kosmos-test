package com.example.rickyandmorty.presentation

sealed class RickyAndMortyState {

    data object Loading : RickyAndMortyState()

    data class Success(
        val results: List<RickyAndMortyUiModel>
    ) : RickyAndMortyState()

    data class Error(
        val errorMsg: String
    ) : RickyAndMortyState()
}