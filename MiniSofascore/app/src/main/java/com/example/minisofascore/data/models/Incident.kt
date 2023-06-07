package com.example.minisofascore.data.models

data class Incident(
    val player: Player,
    val scoringTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val goalType: String,
    val id: Int,
    val time: Int,
    val type: String
)

data class Player(
    val id: Int,
    val name: String,
    val slug: String,
    val country: Country,
    val position: String
)