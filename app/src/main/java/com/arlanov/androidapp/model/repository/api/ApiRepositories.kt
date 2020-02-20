package com.arlanov.androidapp.model.repository.api

import com.arlanov.androidapp.model.models.RepositoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepositories {
    @GET("search/repositories")
    fun getRepositories(@Query("q") search: String): Call<RepositoryModel>
}