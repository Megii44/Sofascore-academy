package com.example.homework_5.model

data class AllLocationsResponse(
    val data: ArrayList<Location>
) : java.io.Serializable

data class Location (
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String
) : java.io.Serializable