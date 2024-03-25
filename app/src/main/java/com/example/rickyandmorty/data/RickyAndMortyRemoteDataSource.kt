package com.example.rickyandmorty.data

import com.example.rickyandmorty.domain.RickyAndMortyItem

interface RickyAndMortyRemoteDataSource {

    suspend fun getItems(): List<RickyAndMortyItem>
}