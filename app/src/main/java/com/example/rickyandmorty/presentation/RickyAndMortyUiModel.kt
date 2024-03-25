package com.example.rickyandmorty.presentation

import com.example.rickyandmorty.domain.RickyAndMortyItem

data class RickyAndMortyUiModel(
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val location: String,
    val type: String?,
)

fun RickyAndMortyItem.toRickyAndMortyUiModel() = RickyAndMortyUiModel(
    name = name,
    image = image,
    status = status,
    species = species,
    gender = gender,
    origin = origin.name,
    location = location.name,
    type = type
)