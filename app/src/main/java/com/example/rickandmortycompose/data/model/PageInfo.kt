package com.example.rickandmortycompose.data.model

import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String
)