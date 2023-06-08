package com.example.minisofascore.data.models

data class CountryFlag(
    val name: String,
    val flags: Flag
): java.io.Serializable

data class Flag(
    val svg: String,
    val png: String,
)