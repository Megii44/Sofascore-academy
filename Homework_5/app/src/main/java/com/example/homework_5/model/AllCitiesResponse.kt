package com.example.homework_5.model

data class AllCitiesResponse(
    val data: ArrayList<City>
) : java.io.Serializable

data class City (
    val id: Int,
) : java.io.Serializable