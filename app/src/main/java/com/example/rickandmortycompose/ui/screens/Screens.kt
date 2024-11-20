package com.example.rickandmortycompose.ui.screens

sealed class Screens(val route: String) {

    data object CharacterScreen : Screens("CharacterScreen")
    data object EpisodeScreen : Screens("EpisodeScreen")
    data object DetailCharacterScreen : Screens("DetailCharacterScreen/{characterId}")
    data object DetailEpisodeScreen : Screens("DetailEpisodeScreen/{episodeId}")
}