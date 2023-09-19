package com.bondstein.onlineattendanceapp.data.remote.api

import com.bondstein.onlineattendanceapp.data.remote.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(APIUrl.EXTENSION_URL_STORES)
    suspend fun callStoresApi(@Query("page") page: Int): BaseModel
}