package com.example.rickandmortycompose.data.network.api

import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.model.CharacterResultsResponse
import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.model.EpisodeResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {

    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodeResultResponse>

    @GET("episode/{id}")
    suspend fun getSingleEpisode(@Path("id") id: Int): Response<EpisodeResponse>

    @GET("character/{id}")
    suspend fun getEpisodeCharacters(@Path("id") id: Int): Response<CharacterResponse>
}