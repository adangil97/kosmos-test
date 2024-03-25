package com.example.rickyandmorty.usecases

import com.example.rickyandmorty.data.RickyAndMortyRepository

class GetRickyAndMortyList(
    private val rickyAndMortyRepository: RickyAndMortyRepository
) {

    suspend operator fun invoke() = rickyAndMortyRepository.getItems()
}