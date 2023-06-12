package com.example.minisofascore.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.minisofascore.data.models.Event
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
            remoteMediator = EventRemoteMediator(remoteDataSource, localDataSource, teamId),
            pagingSourceFactory = { localDataSource.getEvents(teamId) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}
