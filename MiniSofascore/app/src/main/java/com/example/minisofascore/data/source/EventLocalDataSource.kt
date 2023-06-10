package com.example.minisofascore.data.source

import com.example.minisofascore.database.EventDao

class EventLocalDataSource(
    private val eventDao: EventDao
) {
    //fun getEvents(teamId: Int) = eventDao.getAllEvents(teamId)

    //suspend fun saveEvents(events: List<Event>) = eventDao.insertAll(events)

    //suspend fun clearEvents() = eventDao.clearEvents()
}
