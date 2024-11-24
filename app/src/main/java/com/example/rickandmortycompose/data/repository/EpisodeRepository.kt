package com.example.rickandmortycompose.data.repository

import android.util.Log
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.network.api.EpisodeApiService

class EpisodeRepository(
    private val episodeApiService: EpisodeApiService
) {

    suspend fun getAllEpisodes(): List<EpisodeResponse>? {
        return try {
            val response = episodeApiService.getAllEpisodes()

            if (response.isSuccessful) {
                return response.body()?.episodeResponse
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getSingleEpisode(id: Int): EpisodeResponse? {
        return try {
            val response = episodeApiService.getSingleEpisode(id)

            if (response.isSuccessful) {
                val episode = response.body()
                return episode
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getEpisodeCharacters(ids: List<Int>): List<CharacterResponse> {
        return try {
            val responses = ids.map { id ->
                episodeApiService.getEpisodeCharacters(id)
            }
            responses.mapNotNull { it.body() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}