package com.example.rickyandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rickyandmorty.presentation.RickyAndMortyScreen
import com.example.rickyandmorty.ui.theme.RickyAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyAndMortyTheme {
                RickyAndMortyScreen()
            }
        }
    }
}