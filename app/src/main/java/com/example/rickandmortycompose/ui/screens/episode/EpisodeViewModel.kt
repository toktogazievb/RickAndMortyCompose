package com.example.rickandmortycompose.ui.screens.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EpisodeViewModel(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    val episodePagingFlow = episodeRepository.getAllEpisodes().flow.cachedIn(viewModelScope)
}