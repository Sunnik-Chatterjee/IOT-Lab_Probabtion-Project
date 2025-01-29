package com.example.project1.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.Data.model.Anime
import com.example.project1.Data.repo.Repository
import com.example.project1.state.UIstate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _animeList : MutableStateFlow<UIstate<Anime>> = MutableStateFlow(UIstate.Idle)
    val animeList = _animeList.asStateFlow()
    fun getAnime(){
        getAnimeList()
    }
    private fun getAnimeList(){
        _animeList.value=UIstate.Loading
        viewModelScope.launch {
            try {
                val response=repo.getAnimeList()
                _animeList.value=response
            }catch (e: Exception){
                Log.e("Anime","Anime Error")
            }
        }
    }


}
