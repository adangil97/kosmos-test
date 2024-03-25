package com.example.rickyandmorty.usecases

import com.example.rickyandmorty.data.RickyAndMortyRepository
import javax.inject.Inject

class GetRickyAndMortyList @Inject constructor(
    private val rickyAndMortyRepository: RickyAndMortyRepository
) {

    suspend operator fun invoke() = rickyAndMortyRepository.getItems()
}