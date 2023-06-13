package com.example.minisofascore.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey
    val id: Int,
    val slug: String,
    @Embedded(prefix = "tournament_")
    val tournament: Tournament,
    @Embedded(prefix = "home_team_")
    val homeTeam: Team,
    @Embedded(prefix = "away_team_")
    val awayTeam: Team,
    val status: String,
    val startDate: String,
    @Embedded(prefix = "home_score_")
    val homeScore: Score?,
    @Embedded(prefix = "away_score_")
    val awayScore: Score?,
    val winnerCode: String?,
    val round: Int
): java.io.Serializable
