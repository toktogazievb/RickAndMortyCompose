package com.example.rickandmortycompose.data.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.network.api.CharacterApiService

class CharacterRepository(
    private val characterApiService: CharacterApiService
) {

    fun getAllCharacter(): Pager<Int, CharacterResponse> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                CharacterPagingSource(characterApiService)
            }
        )
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