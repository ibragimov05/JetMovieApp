package uz.android.jetmovieapp.feature.home.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.models.MovieResponse
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.common.navigation.Routes


@Composable
fun CategoryMovieContent(movies: MovieResponse, navController: NavController) {
    if (movies.results.isEmpty()) {
        Text("No movies available", color = Color.White)
    } else {
        Column {
            for (i in 0..movies.results.size - 3 step 3) {
                Row {
                    for (j in 0..2) {
                        MovieItem(
                            movies.results[i + j],
                            onClick = {
                                navController.navigate(Routes.MovieDetails.name + "/${movies.results[i + j].id}")
                            },
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun MovieItem(movie: MovieResult, onClick: () -> Unit) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = (displayMetrics.widthPixels / displayMetrics.density) / 3

    Surface(
        modifier = Modifier
            .width(screenWidth.dp)
            .height(180.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
    ) {
        Image(
            painter = rememberAsyncImagePainter(Constants.IMAGES_BASE_URL + movie.poster_path),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
