package com.example.rickandmortycompose.data.repository.character

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.network.api.CharacterApiService

class CharacterPagingSource(
    private val characterApiService: CharacterApiService
) : PagingSource<Int, CharacterResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val currentPage = params.key ?: 1

            val response = characterApiService.getAllCharacters(currentPage)
            val characters = response.characterResponse ?: emptyList()

            val nextPage = if (response.pageInfo?.next == null) {
                null
            } else {
                Uri.parse(response.pageInfo.next).getQueryParameter("page")?.toInt()
            }
            LoadResult.Page(
                data = characters,
                nextKey = nextPage,
                prevKey = if (currentPage == 1) null else currentPage - 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition
    }
}