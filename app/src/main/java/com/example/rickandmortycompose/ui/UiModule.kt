package com.example.rickandmortycompose.ui

import com.example.rickandmortycompose.ui.screens.character.CharacterViewModel
import com.example.rickandmortycompose.ui.screens.character.detail.DetailCharacterViewModel
import com.example.rickandmortycompose.ui.screens.episode.EpisodeViewModel
import com.example.rickandmortycompose.ui.screens.episode.detail.DetailEpisodeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule: Module = module {

    viewModel {
        CharacterViewModel(get())
    }

    viewModel {
        EpisodeViewModel(get())
    }

    viewModel {
        DetailCharacterViewModel(get())
    }

    viewModel {
        DetailEpisodeViewModel(get())
    }
}