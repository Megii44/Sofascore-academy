package com.example.minisofascore.network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {
    private val service: MiniSofascoreApiService
    private val baseUrl = "https://academy.dev.sofascore.com/"

    fun getService(): MiniSofascoreApiService {
        return service
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient).build()
        service = retrofit.create(MiniSofascoreApiService::class.java)
    }
}