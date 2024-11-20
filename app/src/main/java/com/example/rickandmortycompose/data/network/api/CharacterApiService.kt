package com.example.rickandmortycompose.data.network.api

import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.model.CharacterResultsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterResultsResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Response<CharacterResponse>
}