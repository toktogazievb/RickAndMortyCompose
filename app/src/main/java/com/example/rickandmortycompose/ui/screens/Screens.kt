package com.example.rickandmortycompose.ui.screens

sealed class Screens(val route: String) {

    data object CharacterScreen : Screens("CharacterScreen")
    data object DetailCharacterScreen : Screens("DetailCharacterScreen/{characterId}")
    data object EpisodeScreen : Screens("EpisodeScreen")
}