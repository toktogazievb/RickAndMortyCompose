package com.example.rickandmortycompose.ui.screens.character

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = koinViewModel(),
    toDetailCharacterScreen: (characterId: Int) -> Unit

) {
    val characters by viewModel.characterStateFlow.collectAsState()
    LaunchedEffect(Dispatchers.IO) {
        viewModel.getAllCharacters()
    }

    LazyColumn {
        items(characters.size) { index ->
            val character = characters[index]
            CharacterItem(
                photo = character.image,
                name = character.name,
                gender = character.gender,
                location = character.location.name,
                onItemClick = {
                    toDetailCharacterScreen(character.id)
                }
            )
        }
    }
}

@Composable
fun CharacterItem(
    photo: String,
    name: String,
    gender: String,
    location: String,
    onItemClick: () -> Unit
) {
    Row(modifier = Modifier
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
        .clickable { onItemClick() }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(90.dp)
                .padding(4.dp),
            model = photo,
            contentDescription = "image of character"
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
                text = gender,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = location,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
        }
    }
}
