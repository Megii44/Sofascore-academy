package com.example.minisofascore.data.models

data class EventResponse(
    val id: Int,
    val slug: String,
    val tournament: Tournament,
    val homeTeam: Team,
    val awayTeam: Team,
    val status: String,
    val startDate: String,
    val homeScore: Score,
    val awayScore: Score,
    val winnerCode: String,
    val round: Int
)

data class Tournament(
    val id: Int,
    val name: String,
    val slug: String,
    val sport: Sport,
    val country: Country
)

data class Team(
    val id: Int,
    val name: String,
    val country: Country
)

data class Sport(
    val id: Int,
    val name: String,
    val slug: String
)

data class Country(
    val id: Int,
    val name: String
)

data class Score(
    val total: Int,
    val period1: Int,
    val period2: Int,
    val period3: Int,
    val period4: Int,
    val overtime: Int
)
