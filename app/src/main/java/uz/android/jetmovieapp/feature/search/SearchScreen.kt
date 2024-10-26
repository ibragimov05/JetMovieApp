package uz.android.jetmovieapp.feature.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.android.jetmovieapp.common.constants.SpacerH32
import uz.android.jetmovieapp.common.navigation.Routes
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.common.widgets.MovieCard
import uz.android.jetmovieapp.common.widgets.SearchField
import uz.android.jetmovieapp.feature.home.HomeViewModel
import uz.android.jetmovieapp.feature.home.MoviesUiState

@Composable
fun SearchScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val moviesState = homeViewModel.moviesState.collectAsState()

    when (val state = moviesState.value) {
        is MoviesUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is MoviesUiState.Success -> {
            Surface(
                modifier = Modifier
                    .padding(
                        top = statusBarHeight, bottom = 8.dp, end = 16.dp, start = 16.dp
                    )
                    .fillMaxSize(),
                color = AppColors.mainColor,
            ) {
                val allMovies = remember {
                    (state.movies.popular.results + state.movies.nowPlaying.results + state.movies.topRated.results).distinctBy { it.id }
                }

                val filteredMovies = remember(searchQueryState.value, allMovies) {
                    if (searchQueryState.value.isBlank()) {
                        allMovies
                    } else {
                        allMovies.filter { movie ->
                            movie.title.contains(searchQueryState.value, ignoreCase = true)
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        "Search Screen", style = TextStyle(
                            fontSize = 18.sp, fontWeight = FontWeight.W600, color = AppColors.white
                        ), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                    )

                    SpacerH32()

                    SearchField(
                        valueState = searchQueryState, labelId = "Search",
                        onValueChange = {
                            searchQueryState.value = it
                        },
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredMovies) { movie ->
                            MovieCard(
                                movie,
                                onTap = {
                                    navController.navigate(Routes.MovieDetails.name + "/${movie.id}")
                                },
                            )
                        }
                    }
                }
            }
        }

        is MoviesUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.message, color = Color.Red, style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}


