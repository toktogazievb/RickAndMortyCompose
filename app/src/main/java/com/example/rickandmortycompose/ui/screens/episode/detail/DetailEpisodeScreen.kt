package com.example.rickandmortycompose.ui.screens.episode.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailEpisodeScreen(
    viewModel: DetailEpisodeViewModel = koinViewModel(),
    id: Int
) {
    val episode by viewModel.singleEpisodeStateFlow.collectAsState()
    LaunchedEffect(Dispatchers.IO) {
        viewModel.getSingleEpisode(id)
    }
    episode?.let {
        SingleEpisode(
            episode = it.episode,
            airDate = it.airDate,
            url = it.url,
            name = it.name
        )
    }

}


@Composable
fun SingleEpisode(
    episode: String,
    name: String,
    airDate: String,
    url: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = episode,
            fontSize = 24.sp,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 2.dp),
            text = name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = airDate,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.size(16.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = url,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic
        )
    }
}
