package com.zohaib.starzplayllib.data.api

import com.zohaib.starzplayllib.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/multi")
    suspend fun searchMulti(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): ApiResponse
}
