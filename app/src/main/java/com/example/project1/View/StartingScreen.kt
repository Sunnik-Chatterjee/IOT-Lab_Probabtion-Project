package com.example.project1.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigation
import coil.compose.rememberAsyncImagePainter
import com.example.project1.Data.model.Data
import com.example.project1.state.UIstate
import com.example.project1.vm.AnimeViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun StartingScreen(viewModel: AnimeViewModel = hiltViewModel()) {
    val animeItems = viewModel.animeList.collectAsState().value
    LazyColumn {
        when (animeItems) {
            is UIstate.Idle -> {
                viewModel.getAnime()
            }

            is UIstate.Loading -> {

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
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
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(20.dp),
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(items.images?.jpg?.image_url ?: ""),
                    contentDescription = items.title ?: "Anime Image",
                    modifier = Modifier.size(180.dp).clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight()
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = items.title ?: "Unknown Anime",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = items.rating ?: "Unknown Rating",
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}