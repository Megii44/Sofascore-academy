package com.example.minisofascore.data.models

data class Incident(
    val player: Player,
    val scoringTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val goalType: String,
    val id: Int,
    val time: Int,
    val type: String,
    val teamSide: String,
    val color: String,
)