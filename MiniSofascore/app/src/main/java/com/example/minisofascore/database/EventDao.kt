package com.example.minisofascore.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.minisofascore.data.models.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM events WHERE tournament_id = :teamId ORDER BY startDate DESC")
    fun getAllEvents(teamId: Int): PagingSource<Int, Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<Event>)

    @Query("DELETE FROM events")
    suspend fun clearEvents()
}
