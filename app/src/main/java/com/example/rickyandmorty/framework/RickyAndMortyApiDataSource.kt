package com.example.rickyandmorty.framework

import com.example.rickyandmorty.data.RickyAndMortyRemoteDataSource
import com.example.rickyandmorty.domain.RickyAndMortyItem
import javax.inject.Inject

class RickyAndMortyApiDataSource @Inject constructor(
    private val rickyAndMortyService: RickyAndMortyService
) : RickyAndMortyRemoteDataSource {

    override suspend fun getItems(): List<RickyAndMortyItem> {
        val call = rickyAndMortyService.getItems()
        return call.body()?.results.orEmpty()
    }
}