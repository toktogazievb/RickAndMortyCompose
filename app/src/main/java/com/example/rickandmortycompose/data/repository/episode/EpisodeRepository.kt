package com.example.rickandmortycompose.data.repository.episode

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.network.api.EpisodeApiService

class EpisodeRepository(
    private val episodeApiService: EpisodeApiService
) {

    fun getAllEpisodes(): Pager<Int,EpisodeResponse> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(episodeApiService)
            }
        )
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