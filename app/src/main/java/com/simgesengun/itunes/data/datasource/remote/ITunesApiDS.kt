package com.simgesengun.itunes.data.datasource.remote

import com.simgesengun.itunes.data.datasource.remote.model.ResponseSearch
import com.simgesengun.itunes.network.model.NetworkResponse
import javax.inject.Singleton
import retrofit2.http.GET
import retrofit2.http.Query

@Singleton
interface ITunesApiDS {

    @GET("search")
    suspend fun search(
        @Query("term") term: String
    ): NetworkResponse<ResponseSearch, Any>


    @GET("search")
    suspend fun searchWithPagination(
        @Query("term") term: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NetworkResponse<ResponseSearch, Any>
}