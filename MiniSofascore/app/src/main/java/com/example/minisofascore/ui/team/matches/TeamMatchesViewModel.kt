package com.example.minisofascore.ui.team.matches

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.network.Network
import kotlinx.coroutines.flow.Flow

class TeamMatchesViewModel : ViewModel() {

    private val network = Network()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTeamEventsStream(id: Int, span: String): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { TeamEventsPagingSource(network.getService(), id, span) }
        ).flow.cachedIn(viewModelScope)
    }
}
