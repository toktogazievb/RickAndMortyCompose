package com.example.rickandmortycompose.data.repository

import android.util.Log
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.network.api.CharacterApiService

class CharacterRepository(
    private val characterApiService: CharacterApiService
) {

    suspend fun getAllCharacter(): List<CharacterResponse>? {
        return try {
            val response = characterApiService.getAllCharacters()

            if (response.isSuccessful) {
                return response.body()?.characterResponse
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getSingleCharacter(id: Int): CharacterResponse? {
        return try {
            val response = characterApiService.getSingleCharacter(id)

            if (response.isSuccessful) {
                val character = response.body()
                return character
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}