package com.example.minisofascore.data.paging

import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.data.repositories.MatchRepository
import java.io.IOException
import retrofit2.HttpException

@ExperimentalPagingApi
class EventRemoteMediator(
    private val eventRepository: MatchRepository,
    private val teamId: Int
) : RemoteMediator<Int, Event>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Event>): MediatorResult {
        return try {
            // Get the page key from the LoadType
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }
            }

            // Fetch data from network
            //val response = eventRepository.getEvents(teamId, "P${page}D", page)
            val response = eventRepository.getEvents(teamId)

            // Store the data in local database
            //eventRepository.saveEvents(response.data)

            MediatorResult.Success(endOfPaginationReached = response.asLiveData().isInitialized)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
