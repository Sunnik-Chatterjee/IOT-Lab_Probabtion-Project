package com.example.project1.Data.repo

import android.util.Log
import com.example.project1.Data.model.Anime
import com.example.project1.Data.model.AnimeDetail
import com.example.project1.Data.remote.API
import com.example.project1.state.UIstate
import javax.inject.Inject

class Repository @Inject constructor(private val api: API) {

    // Fetch Anime List
    suspend fun getAnimeList(): UIstate<Anime> {
        return try {
            val response = api.getAllDetails(query = "") // Fetch all anime with no filter
            if (response.isSuccessful && response.body() != null) {
                UIstate.Success(response.body())
            } else {
                UIstate.Error("Failed to fetch anime list")
            }
        }catch (e:Exception){
            UIstate.Error(e.message ?: "Network request failed")
        }
    }

}
