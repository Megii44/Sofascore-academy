package com.example.minisofascore.data.repositories

import com.example.minisofascore.data.models.CountryInfo
import com.example.minisofascore.network.CountryNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository {
    private val networkService = CountryNetwork().getService()

    suspend fun fetchCountryFlag(countryName: String): List<CountryInfo> = withContext(Dispatchers.IO) {
        val response = networkService.fetchCountryFlag(countryName)
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Received empty response body")
        } else {
            throw Exception("Failed to fetch country flag: ${response.message()}")
        }
    }
}
