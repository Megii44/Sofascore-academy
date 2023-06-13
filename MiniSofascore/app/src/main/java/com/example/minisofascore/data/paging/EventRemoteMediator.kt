package com.example.minisofascore.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.data.source.EventLocalDataSource
import com.example.minisofascore.data.source.EventRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class EventRemoteMediator(
    private val remoteDataSource: EventRemoteDataSource,
    private val localDataSource: EventLocalDataSource,
    private val teamId: Int
) : RemoteMediator<Int, Event>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Event>): MediatorResult {
        return try {
            // Get the page key from the LoadType
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> state.pages.size + 1
            }

            // Fetch data from network
            val response = remoteDataSource.fetchEvents(teamId, "next", page)

            // Clear old data on refresh
            //if (loadType == LoadType.REFRESH) {
            //    localDataSource.clearEvents()
            //}

            // Store the data in local database
            localDataSource.saveEvents(response)

            // End of pagination is reached when response is empty
            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
