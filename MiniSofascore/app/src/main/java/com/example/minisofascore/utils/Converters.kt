package com.example.minisofascore.utils

import androidx.room.TypeConverter
import com.example.minisofascore.data.models.Sport
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromSport(sport: Sport): String {
        return Gson().toJson(sport)
    }

    @TypeConverter
    fun toSport(sportString: String): Sport {
        val sportType = object : TypeToken<Sport>() {}.type
        return Gson().fromJson(sportString, sportType)
    }
}
