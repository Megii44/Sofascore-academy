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
): java.io.Serializable

data class Tournament(
    val id: Int,
    val name: String,
    val slug: String,
    val sport: Sport,
    val country: Country,
    var logo: String?,
): java.io.Serializable

data class Team(
    val id: Int,
    val name: String,
    val country: Country,
    var logo: String,
): java.io.Serializable

data class Sport(
    val id: Int,
    val name: String,
    val slug: String
): java.io.Serializable

data class Country(
    val id: Int,
    val name: String
): java.io.Serializable

data class Score(
    val total: Int,
    val period1: Int,
    val period2: Int,
    val period3: Int,
    val period4: Int,
    val overtime: Int
): java.io.Serializable
