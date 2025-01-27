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
    suspend fun getAnimeList(
        @Query("page") page: Int = 1 // Example query parameter for pagination
    ): Response<Anime>

    @GET("anime/{animeID}")
    suspend fun getAnimeInfo(
        @Path("animeID") animeID: Int
    ): Response<AnimeDetail>
}