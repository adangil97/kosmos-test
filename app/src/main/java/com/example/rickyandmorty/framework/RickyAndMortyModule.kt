package com.example.rickyandmorty.framework

import com.example.rickyandmorty.data.RickyAndMortyRepository
import com.example.rickyandmorty.usecases.GetRickyAndMortyList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://rickandmortyapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun rickyAndMortyService(
    retrofit: Retrofit = retrofit()
): RickyAndMortyService = retrofit.create(RickyAndMortyService::class.java)

fun rickyAndMortyRemoteDataSource(
    rickyAndMortyService: RickyAndMortyService = rickyAndMortyService()
) = RickyAndMortyApiDataSource(rickyAndMortyService)

fun rickyAndMortyRepository(
    rickyAndMortyApiDataSource: RickyAndMortyApiDataSource = rickyAndMortyRemoteDataSource()
) = RickyAndMortyRepository(rickyAndMortyApiDataSource)

fun getRickyAndMortyList(
    rickyAndMortyRepository: RickyAndMortyRepository = rickyAndMortyRepository()
) = GetRickyAndMortyList(rickyAndMortyRepository)