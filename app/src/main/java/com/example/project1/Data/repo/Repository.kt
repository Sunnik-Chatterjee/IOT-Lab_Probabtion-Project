package com.example.project1.Data.repo

import com.example.project1.Data.model.Anime
import com.example.project1.Data.model.AnimeDetail
import com.example.project1.Data.remote.API
import com.example.project1.state.UIstate
import javax.inject.Inject

class Repository @Inject constructor(private val api: API) {

    // Fetch Anime List
    suspend fun getAnimeList(page: Int): UIstate<Anime> {
        return try {
            val response = api.getAnimeList(page = page)
            if (response.isSuccessful) {
                UIstate.Success(response.body()!!)
            } else {
                UIstate.Error("Failed to fetch anime list: ${response.message()}")
            }
        } catch (e: Exception) {
            UIstate.Error("Exception occurred: ${e.localizedMessage}")
        }
    }

    // Fetch Anime Details
    suspend fun getAnimeDetails(id: Int): UIstate<AnimeDetail> {
        return try {
            val response = api.getAnimeInfo(id)
            if (response.isSuccessful) {
                UIstate.Success(response.body()!!)
            } else {
                UIstate.Error("Failed to fetch anime details: ${response.message()}")
            }
        } catch (e: Exception) {
            UIstate.Error("Exception occurred: ${e.localizedMessage}")
        }
    }
}
