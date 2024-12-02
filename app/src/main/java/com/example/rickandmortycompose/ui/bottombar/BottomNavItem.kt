package com.example.rickandmortycompose.ui.bottombar

import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.screens.Screens

sealed class BottomNavItem(val route:String, val label:String, val icon: Int) {
    data object Characters:BottomNavItem(Screens.CharacterScreen.route, "Characters", R.drawable.ic_character)
    data object Episodes:BottomNavItem(Screens.EpisodeScreen.route, "Episodes", R.drawable.ic_episode)
}