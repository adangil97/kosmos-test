package com.example.rickyandmorty.framework

import com.example.rickyandmorty.data.RickyAndMortyRemoteDataSource
import com.example.rickyandmorty.domain.RickyAndMortyItem

class RickyAndMortyApiDataSource(
    private val rickyAndMortyService: RickyAndMortyService
) : RickyAndMortyRemoteDataSource {

    override suspend fun getItems(): List<RickyAndMortyItem> {
        val call = rickyAndMortyService.getItems()
        return call.body()?.results.orEmpty()
    }
}