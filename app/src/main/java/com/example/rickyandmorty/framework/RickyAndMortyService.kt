package com.example.rickyandmorty.framework

import com.example.rickyandmorty.domain.RickyAndMorty
import retrofit2.Response
import retrofit2.http.GET

interface RickyAndMortyService {

    @GET("character")
    suspend fun getRickyAndMortyCharacterList(): Response<RickyAndMorty>
}