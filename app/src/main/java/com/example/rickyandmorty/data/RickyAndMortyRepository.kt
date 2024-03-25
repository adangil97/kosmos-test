package com.example.rickyandmorty.data

import javax.inject.Inject

class RickyAndMortyRepository @Inject constructor(
    private val rickyAndMortyRemoteDataSource: RickyAndMortyRemoteDataSource
) {

    suspend fun getItems() = rickyAndMortyRemoteDataSource.getItems()
}