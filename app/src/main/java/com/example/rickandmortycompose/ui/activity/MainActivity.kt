package com.example.rickandmortycompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.screens.CharacterScreen
import com.example.rickandmortycompose.ui.screens.EpisodeScreen
import com.example.rickandmortycompose.ui.screens.Screens
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                RickAndMortyApp()
            }
        }
    }

    @Composable
    private fun RickAndMortyApp() {
        val navController = rememberNavController()

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar()
            },
            bottomBar = {
                BottomBar(navController)
            }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screens.EpisodeScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screens.EpisodeScreen.route) {
                    EpisodeScreen()
                }
                composable(Screens.CharacterScreen.route) {
                    CharacterScreen()
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Color.Cyan),
        title = {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Rick and Morty"
            )
        }
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(Screens.CharacterScreen, Screens.EpisodeScreen)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(
        containerColor = Color.Cyan,
        contentColor = Color.Yellow
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (screen == Screens.CharacterScreen) R.drawable.ic_character
                            else R.drawable.ic_episode
                        ),
                        contentDescription = screen.route
                    )
                },
                label = {
                    Text(
                        text = if (screen == Screens.CharacterScreen) "Characters"
                        else "Episodes"
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}