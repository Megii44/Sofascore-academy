package com.example.minisofascore.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.data.paging.EventPagingSource
import com.example.minisofascore.data.paging.EventRemoteMediator
import com.example.minisofascore.data.source.EventLocalDataSource
import com.example.minisofascore.data.source.EventRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MatchRepository (
    private val remoteDataSource: EventRemoteDataSource,
    private val localDataSource: EventLocalDataSource
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getEvents(teamId: Int): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
            remoteMediator = EventRemoteMediator(this, teamId),
            pagingSourceFactory = { EventPagingSource(this, teamId) }
        ).flow
    }

    suspend fun refreshEvents(teamId: Int, span: String, page: Int) {
        val events = remoteDataSource.fetchEvents(teamId, span, page)
        //localDataSource.clearEvents()
        //localDataSource.saveEvents(events)
    }

    suspend fun fetchEvents(teamId: Int, span: String, page: Int): List<Event> {
        return remoteDataSource.fetchEvents(teamId, span, page)
    }

    suspend fun saveEvents(events: List<Event>) {
        //localDataSource.saveEvents(events)
    }

    suspend fun fetchEventsPage(teamId: Int, page: Int): List<Event> {
        return remoteDataSource.fetchEvents(teamId,"next" ,page)
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}
