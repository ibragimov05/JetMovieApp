package uz.android.jetmovieapp.feature.watchlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.R
import uz.android.jetmovieapp.common.constants.SpacerH12
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.common.navigation.Routes
import uz.android.jetmovieapp.common.widgets.MovieCard

@Composable
fun WatchlistScreen(
    navController: NavController, watchListViewModel: WatchListViewModel,
) {
    val favMovies = watchListViewModel.favList.collectAsState().value
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = statusBarHeight),
        color = AppColors.mainColor,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Watch list",
                    style = MaterialTheme.typography.titleLarge,
                    color = AppColors.white
                )
            }
            SpacerH12()
            if (favMovies.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.magicbox),
                            contentDescription = "Empty watchlist",
                            modifier = Modifier.size(80.dp)
                        )
                    },
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(favMovies) { movie ->
                        MovieCard(
                            MovieResult(
                                adult = movie.adult,
                                backdrop_path = movie.backdrop_path,
                                genre_ids = listOf(),
                                id = movie.id,
                                original_language = movie.original_language,
                                original_title = movie.original_title,
                                overview = movie.overview,
                                popularity = movie.popularity,
                                poster_path = movie.poster_path,
                                release_date = movie.release_date,
                                title = movie.title,
                                video = movie.video,
                                vote_average = movie.vote_average,
                                vote_count = movie.vote_count,
                            ),
                            onTap = {
                                navController.navigate(Routes.MovieDetails.name + "/${movie.id}")
                            },
                        )
                    }
                }
            }
        }

    }
}