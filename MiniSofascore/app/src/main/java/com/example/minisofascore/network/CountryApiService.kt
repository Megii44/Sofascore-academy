package com.example.minisofascore.network

import com.example.minisofascore.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {

    @GET("/v2/name/{country_name}")
    suspend fun fetchCountryFlag(
        @Path("country_name") countryName: String
    ): Response<List<CountryInfo>>

}
