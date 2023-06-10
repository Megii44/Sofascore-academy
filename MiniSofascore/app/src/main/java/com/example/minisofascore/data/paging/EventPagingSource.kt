package com.example.minisofascore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.data.repositories.MatchRepository
import retrofit2.HttpException
import java.io.IOException

class EventPagingSource(
    private val eventRepository: MatchRepository,
    private val teamId: Int
) : PagingSource<Int, Event>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        return try {
            val nextPage = params.key ?: 1
            val response = eventRepository.fetchEventsPage(teamId, nextPage)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.isEmpty()) null else nextPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Event>): Int? {
        return state.anchorPosition
    }
}
