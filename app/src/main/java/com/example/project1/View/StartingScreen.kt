package com.example.project1.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.project1.Data.model.Data
import com.example.project1.state.UIstate
import com.example.project1.vm.AnimeViewModel

@Composable
fun StartingScreen(viewModel: AnimeViewModel = hiltViewModel()) {
    val animeItems = viewModel.animeList.collectAsState().value
    LazyColumn {
        when (animeItems) {
            is UIstate.Idle -> {
                viewModel.getAnime()
            }

            is UIstate.Loading -> {
                item { CircularProgressIndicator() }
            }
            is UIstate.Success -> {
                val animeList=animeItems.data?.data
                if (!animeList.isNullOrEmpty()){
                    items(animeList){
                        item->
                        AnimeItemRow(item)
                    }
                }else {
                    item { Text("No Anime Data Available") }
                }
            }
            else -> {}
        }
    }
}
@Composable
fun AnimeItemRow(items: Data) {
    Card() {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(items.images?.jpg?.image_url ?: ""),
                contentDescription = items.title?:"Anime Image",
                modifier = Modifier.size(64.dp) // Adjust size as needed
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = items.title?:"Unknown Anime", fontStyle = FontStyle.Italic)
        }
    }
}
