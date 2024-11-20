package com.example.rickandmortycompose.ui.screens.episode

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EpisodeScreen() {
    LazyColumn {
        items(100) { index ->
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Episode $index"
            )
        }
    }
}