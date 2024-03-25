package com.example.rickyandmorty.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showSystemUi = true)
fun RickyAndMortyPrev() {
    RickyAndMortyListContent(
        result = listOf(
            RickyAndMortyUiModel(
                "Fake 1",
                "",
                status = "status 1",
                species = "species 1",
                gender = "gender 1",
                origin = "origin 1",
                location = "location 1",
                type = "type 1"
            ),
            RickyAndMortyUiModel(
                "Fake 2",
                "",
                status = "status 2",
                species = "species 2",
                gender = "gender 2",
                origin = "origin 2",
                location = "location 2",
                type = "type 2"
            ),
            RickyAndMortyUiModel(
                "Fake 3",
                "",
                status = "status 3",
                species = "species 3",
                gender = "gender 3",
                origin = "origin 3",
                location = "location 3",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 4",
                "",
                status = "status 4",
                species = "species 4",
                gender = "gender 4",
                origin = "origin 4",
                location = "location ",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 5",
                "",
                status = "status ",
                species = "species 5",
                gender = "gender 5",
                origin = "origin 5",
                location = "location 5",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 6",
                "",
                status = "status 6",
                species = "species 6",
                gender = "gender 6",
                origin = "origin 6",
                location = "location 6",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 7",
                "",
                status = "status 7",
                species = "species 7",
                gender = "gender 7",
                origin = "origin 7",
                location = "location 7",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 8",
                "",
                status = "status 8",
                species = "species 8",
                gender = "gender 8",
                origin = "origin 8",
                location = "location 8",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 9",
                "",
                status = "status 9",
                species = "species 9",
                gender = "gender 9",
                origin = "origin 9",
                location = "location 9",
                type = null
            ),
            RickyAndMortyUiModel(
                "Fake 10",
                "",
                status = "status 10",
                species = "species 10",
                gender = "gender 10",
                origin = "origin 10",
                location = "location 10",
                type = null
            ),
        )
    )
}

@Composable
@Preview(showSystemUi = true)
fun RickyAndMortyLoadingPreview() {
    RickyAndMortyLoadingContent()
}