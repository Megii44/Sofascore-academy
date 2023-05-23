package com.example.minisofascore.data.models

data class Event(
    val id: Int,
    val homeTeam: Team,
    val awayTeam: Team,
    val status: String,
    val startDate: String,
    val homeScore: Score,
    val awayScore: Score,
    val winnerCode: String,
    val logo: String,
)