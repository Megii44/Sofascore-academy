package com.example.minisofascore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.data.models.Tournament

@Database(entities = [Tournament::class, Event::class], version = 2, exportSchema = false)
abstract class MiniSofascoreDatabase: RoomDatabase() {

    abstract fun EventDao(): EventDao

    companion object {
        private var instance: MiniSofascoreDatabase? = null

        fun getDatabase(context: Context): MiniSofascoreDatabase? {
            if(instance == null) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MiniSofascoreDatabase::class.java,
            name = "MiniSofascoreDb.db"
        ).build()
    }
}