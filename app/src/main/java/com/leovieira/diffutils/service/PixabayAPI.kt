package com.leovieira.diffutils.service

import com.leovieira.diffutils.model.Pixabay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun getImage(
        @Query("key") key: String = "23329521-13d6e7bf746e587949c44bc6c",
        @Query("q") q: String,
        @Query("lang") lang: String = "pt"
    ) : Response<Pixabay>

}