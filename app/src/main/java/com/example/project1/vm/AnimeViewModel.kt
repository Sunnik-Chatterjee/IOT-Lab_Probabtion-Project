package com.example.project1.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1.Data.model.Anime
import com.example.project1.Data.model.AnimeDetail
import com.example.project1.Data.model.Data
import com.example.project1.Data.remote.API
import com.example.project1.Data.repo.Repository
import com.example.project1.state.UIstate
import com.google.android.gms.common.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val service:API):ViewModel() {
    private val allAnimeList = MutableStateFlow<UIstate<Anime>>(UIstate.Idle)
    private val animeDetail=MutableStateFlow<UIstate<AnimeDetail>>(UIstate.Idle)
    fun fetchAnimeList(page:Int =1){
    allAnimeList.value=UIstate.Loading
        viewModelScope.launch {
            val result = Repository.ge
            allAnimeList.value=result
        }

    }
}