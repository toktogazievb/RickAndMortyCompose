package com.example.rickandmortycompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Animation
import androidx.compose.animation.core.Transition
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.bottombar.BottomNavItem
import com.example.rickandmortycompose.ui.screens.character.CharacterScreen
import com.example.rickandmortycompose.ui.screens.episode.EpisodeScreen
import com.example.rickandmortycompose.ui.screens.Screens
import com.example.rickandmortycompose.ui.screens.character.detail.DetailCharacterScreen
import com.example.rickandmortycompose.ui.screens.episode.detail.DetailEpisodeScreen
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
        Box() {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = R.drawable.bg_content,
                contentDescription = "background color",
            )
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopBar()
                },
                bottomBar = {
                    BottomBar(navController)
                }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screens.CharacterScreen.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screens.CharacterScreen.route,
                        enterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                            slideInHorizontally()
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                            shrinkHorizontally()
                        },
                        popEnterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                            slideInHorizontally()
                        },
                        popExitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                            shrinkHorizontally()
                        }) {
                        CharacterScreen(toDetailCharacterScreen = { characterId ->
                            navController.navigate("DetailCharacterScreen/$characterId")
                        })
                    }
                    composable(Screens.EpisodeScreen.route,
                        enterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                            slideInHorizontally()
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                            shrinkHorizontally()
                        },
                        popEnterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                            slideInHorizontally()
                        },
                        popExitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                            shrinkHorizontally()
                        }) {
                        EpisodeScreen(toDetailEpisodeScreen = { episodeId ->
                            navController.navigate("DetailEpisodeScreen/$episodeId")
                        })
                    }
                    composable(
                        route = Screens.DetailCharacterScreen.route,
                        arguments = listOf(navArgument(name = "characterId") {
                            type = NavType.IntType
                        }),
                        enterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                        }
                    ) { backStackEntry ->
                        val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
                        DetailCharacterScreen(id = characterId)
                    }
                    composable(
                        route = Screens.DetailEpisodeScreen.route,
                        arguments = listOf(navArgument(name = "episodeId") {
                            type = NavType.IntType
                        }),
                        enterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                        }
                    ) { backStackEntry ->
                        val episodeId = backStackEntry.arguments?.getInt("episodeId") ?: 0
                        DetailEpisodeScreen(
                            episodeId = episodeId,
                            toDetailCharacterScreen = { characterId ->
                                navController.navigate("DetailCharacterScreen/$characterId")
                            })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.purple_500)),
        title = {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                text = "Rick and Morty"
            )
        }
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(BottomNavItem.Characters, BottomNavItem.Episodes)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(
        containerColor = colorResource(R.color.purple_500),
        contentColor = Color.Green
    ) {
        items.forEach { item ->
            val selectedItem = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        color = Color.White,
                        text = item.label
                    )
                },
                selected = selectedItem,
                onClick = {
                    navController.navigate(item.route) {
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

@Composable
fun CustomLinearProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            modifier = Modifier.width(120.dp),
            color = colorResource(R.color.teal_200),
            trackColor = colorResource(R.color.teal_700)
        )
    }
}

@Composable
fun CustomCircularProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier.width(40.dp),
        color = colorResource(R.color.teal_200),
        trackColor = colorResource(R.color.teal_700)
    )
}
