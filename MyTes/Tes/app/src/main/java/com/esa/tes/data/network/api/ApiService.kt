package com.esa.tes.data.network.api

import com.esa.tes.data.network.response.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int?,
    ): ResponseData

}