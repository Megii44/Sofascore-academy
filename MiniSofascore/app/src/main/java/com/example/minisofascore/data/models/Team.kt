package com.example.minisofascore.data.models

data class Team(
    val id: Int,
    val name: String,
    val country: Country,
    var logo: String,
    val managerName: String,
    val venue: String,
): java.io.Serializable