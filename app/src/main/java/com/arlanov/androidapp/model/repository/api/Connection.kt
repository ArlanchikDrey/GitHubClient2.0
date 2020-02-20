package com.arlanov.androidapp.model.repository.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connection {
    const val API_BASE_URL = "https://api.github.com/"

    private val loggingInterceptor = run {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun getApiRepositories(): ApiRepositories{
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiRepositories::class.java)
    }
}