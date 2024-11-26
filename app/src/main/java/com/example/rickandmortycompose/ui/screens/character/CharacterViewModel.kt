package com.example.rickandmortycompose.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.repository.character.CharacterRepository

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val charactersPagingFlow = characterRepository.getAllCharacter().flow.cachedIn(viewModelScope)
}