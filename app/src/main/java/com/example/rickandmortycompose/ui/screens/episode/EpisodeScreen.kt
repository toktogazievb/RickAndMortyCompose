package com.example.rickandmortycompose.ui.screens.episode

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeScreen(
    viewModel: EpisodeViewModel = koinViewModel(),
    toDetailEpisodeScreen: (episodeId: Int) -> Unit
) {
    val episodes by viewModel.episodeStateFlow.collectAsState()
    LaunchedEffect(Dispatchers.IO) {
        viewModel.getAllEpisodes()
    }
    Log.d("EpisodeScreen", "Fetched episodes: $episodes")

    LazyColumn {
        items(episodes.size) { index ->
            val item = episodes[index]
            EpisodeItem(
                episode = item.episode,
                name = item.name,
                airDate = item.airDate,
                onItemClick = {
                    toDetailEpisodeScreen(item.id)
                }
            )
            Log.d("EpisodeScreen", "Fetched episodes: $episodes")
        }
    }
}


@Composable
fun EpisodeItem(
    episode: String,
    name: String,
    airDate: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(2.dp)
            )
            .border(
                border = BorderStroke(
                    1.dp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(2.dp)
            )
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = episode
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 2.dp),
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp, top = 8.dp),
                text = airDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
        }
    }
}

