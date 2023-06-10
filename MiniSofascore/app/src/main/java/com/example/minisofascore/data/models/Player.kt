package com.example.minisofascore.data.models

data class Player(
    val id: Int,
    val name: String,
    val slug: String,
    val country: Country,
    val position: String
)