package com.example.rickyandmorty.data

class RickyAndMortyRepository(
    private val rickyAndMortyRemoteDataSource: RickyAndMortyRemoteDataSource
) {

    suspend fun getItems() = rickyAndMortyRemoteDataSource.getItems()
}