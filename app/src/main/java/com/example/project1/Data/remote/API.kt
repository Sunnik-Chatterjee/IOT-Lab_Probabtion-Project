package com.example.project1.Data.remote

import com.example.project1.Data.model.Anime
import com.example.project1.Data.model.AnimeDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface API {
    @GET("anime")
    suspend fun getAllDetails(
        @Query("q") query: String
    ): Response<Anime>

    @GET("anime/{animeID}")
    suspend fun getAnimeInfo(
        @Path("animeID") animeID: Int
    ): Response<AnimeDetail>
}