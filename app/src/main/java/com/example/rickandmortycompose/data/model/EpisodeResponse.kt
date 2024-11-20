package com.example.rickandmortycompose.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeResultResponse(
    @SerializedName("results")
    val episodeResponse: List<EpisodeResponse>
)

data class EpisodeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("created")
    val created: String,
)