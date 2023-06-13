package com.example.minisofascore.data.models

data class Tournament(
    val id: Int,
    val name: String,
    val slug: String,
    val sport: Sport,
    val country: Country,
    var logo: String?,
): java.io.Serializable
