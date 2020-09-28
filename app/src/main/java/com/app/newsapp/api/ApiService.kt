package com.app.newsapp.api


import com.app.newsapp.data.AppResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiEndpoints.TOP_HEADLINES)
    suspend fun getNewsList(
        @Query(ApiParameters.COUNTRY) country: String,
        @Query(ApiParameters.CATEGORY) category: String,
        @Query(ApiParameters.API_KEY) apiKey: String
    ): AppResponse
}