package com.example.rickandmortycompose.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _characterStateFlow = MutableStateFlow<List<CharacterResponse>>(emptyList())
    val characterStateFlow = _characterStateFlow.asStateFlow()

    fun getAllCharacters() {
        viewModelScope.launch {
            val characters = characterRepository.getAllCharacter()
            characters?.let {
                _characterStateFlow.value = characters
            }
        }
    }
}