package com.example.rickandmortycompose.ui.screens.character.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailCharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _singleCharacterStateFlow = MutableStateFlow<CharacterResponse?>(null)
    val singleCharacterStateFlow = _singleCharacterStateFlow.asStateFlow()

    fun getSingleCharacter(id: Int) {
        viewModelScope.launch {
            val character = characterRepository.getSingleCharacter(id)
            character?.let {
                _singleCharacterStateFlow.value = character
            }
        }
    }
}