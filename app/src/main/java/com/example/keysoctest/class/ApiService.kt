package com.example.keysoctest.`class`

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search")
    fun itunesSearch(@Query("term") term: String? = "jack+johnson",
                     @Query("entity") entity: String? = "album"): Call<KSAlbumsResponse>




}