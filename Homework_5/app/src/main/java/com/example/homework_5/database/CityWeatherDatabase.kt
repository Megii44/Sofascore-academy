package com.example.homework_5.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homework_5.model.CurrentLocationWeatherResponse

@Database(entities = [CurrentLocationWeatherResponse::class, CurrentLocationWeatherResponse::class], version = 1, exportSchema = false)
abstract class CityWeatherDatabase: RoomDatabase() {

    abstract fun CityWeatherDao(): CityWeatherDao

    companion object {
        private var instance: CityWeatherDatabase? = null

        fun getDatabase(context: Context): CityWeatherDatabase? {
            if(instance == null) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CityWeatherDatabase::class.java,
            name = "CityWeatherDb.db"
        ).build()
    }
}