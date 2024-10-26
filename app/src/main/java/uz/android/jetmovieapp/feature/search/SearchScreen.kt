package uz.android.jetmovieapp.feature.search


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.constants.SpacerH32
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.common.navigation.Routes
import uz.android.jetmovieapp.common.theme.AppColors
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

                    SearchField(valueState = searchQueryState, labelId = "Search", onValueChange = {
                        searchQueryState.value = it
                    })

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


@Composable
private fun MovieCard(
    movie: MovieResult,
    onTap: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.mainColor.copy(alpha = 0.7f))
            .clickable {
                onTap()
            },
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Movie Poster
            AsyncImage(
                model = "${Constants.IMAGES_BASE_URL}${movie.poster_path}",
                contentDescription = movie.title,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Movie Info
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Title
                Text(
                    text = movie.title, style = TextStyle(
                        fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White
                    ), maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))


                Spacer(modifier = Modifier.height(4.dp))

                // Year
                Text(
                    text = movie.release_date.take(4), style = TextStyle(
                        fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f)
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Duration (if available in your model)
                Text(
                    text = "139 minutes",  // You might want to add runtime to your model
                    style = TextStyle(
                        fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f)
                    )
                )

                // Rating
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f", movie.vote_average), style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFFFFC107),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}