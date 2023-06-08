package com.example.minisofascore.ui.team.matches

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.network.MiniSofascoreApiService

class TeamEventsPagingSource(
    private val apiService: MiniSofascoreApiService,
    private val id: Int,
    private val span: String
) : PagingSource<Int, EventResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventResponse> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getTeamEvents(id, span, position)
            val events = response.body()

            LoadResult.Page(
                data = events.orEmpty(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (events.isNullOrEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EventResponse>): Int? {
        return state.anchorPosition
    }
}
