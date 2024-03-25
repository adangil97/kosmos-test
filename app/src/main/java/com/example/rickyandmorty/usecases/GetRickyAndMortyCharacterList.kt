package com.example.rickyandmorty.usecases

import com.example.rickyandmorty.data.RickyAndMortyRepository
import javax.inject.Inject

class GetRickyAndMortyCharacterList @Inject constructor(
    private val rickyAndMortyRepository: RickyAndMortyRepository
) {

    suspend operator fun invoke() = rickyAndMortyRepository.getRickyAndMortyCharacterList()
}