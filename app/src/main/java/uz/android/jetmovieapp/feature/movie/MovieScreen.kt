package uz.android.jetmovieapp.feature.movie

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.feature.home.HomeViewModel
import uz.android.jetmovieapp.R
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.constants.SpacerH12
import uz.android.jetmovieapp.common.constants.SpacerH16
import uz.android.jetmovieapp.common.constants.SpacerH24
import uz.android.jetmovieapp.common.models.MovieRoomResult
import uz.android.jetmovieapp.feature.movie.widgets.AboutMovieContent
import uz.android.jetmovieapp.feature.movie.widgets.CastContent
import uz.android.jetmovieapp.feature.movie.widgets.MovieInfoItem
import uz.android.jetmovieapp.feature.watchlist.WatchListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavController,
    movieId: String,
    homeVM: HomeViewModel,
    movieViewModel: MovieViewModel,
    watchListViewModel: WatchListViewModel,
) {
    val movie = homeVM.getMovieById(movieId)
    var selectedTabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf("About Movie", "Casts")
    Log.d("MOVIES", "MovieScreen: ${movie?.id}")
    val initialFavoriteState = movie?.let { watchListViewModel.isFavorite(it.id) } ?: false
    val isFavorite = remember { mutableStateOf(initialFavoriteState) }
    val context = LocalContext.current


    if (movie == null) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColors.mainColor,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Something went wrong. Movie not found",
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = AppColors.white,
                    ),
                    textAlign = TextAlign.Center,
                )
                Image(
                    painter = painterResource(id = R.drawable.popcorn),
                    contentDescription = "Movie not found"
                )
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        }
        return
    }

    Scaffold(
        containerColor = AppColors.mainColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.mainColor,
                ),
                title = {
                    Text(text = "Detail", color = AppColors.white)
                },
                actions = {
                    IconButton(onClick = {
                        // Update local state immediately
                        isFavorite.value = !isFavorite.value

                        // Perform database operation
                        watchListViewModel.toggleFavorite(
                            MovieRoomResult(
                                id = movie.id,
                                adult = movie.adult,
                                backdrop_path = movie.backdrop_path,
                                genre_ids = movie.genre_ids.toString(),
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
                            )
                        )

                        // Show feedback
                        Toast.makeText(
                            context,
                            if (isFavorite.value) "Added to favorites" else "Removed from favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isFavorite.value) "Remove from favorites" else "Add to favorites",
                            tint = if (isFavorite.value) Color.Red else LocalContentColor.current
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                    ) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Close",
                            tint = AppColors.white,
                        )
                    }
                },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(Constants.IMAGES_BASE_URL + movie.backdrop_path),
                        contentDescription = "Movie backdrop",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth,
                    )
                }
                SpacerH12()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        movie.title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = AppColors.white,
                        ),
                    )
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .wrapContentSize(),
                        color = Color.Black.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = "Rating",
                                tint = Color(0xFFFF8700),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "%.1f".format(movie.vote_average), style = TextStyle(
                                    color = Color(0xFFFF8700),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
                SpacerH16()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    MovieInfoItem(icon = Icons.Filled.DateRange, text = movie.release_date)
                    MovieInfoItem(icon = Icons.Default.Info, text = movie.original_language)
                }
                SpacerH24()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Column(modifier = Modifier
                                .weight(1f)
                                .clickable { selectedTabIndex.intValue = index }) {
                                Text(
                                    text = title,
                                    color = if (selectedTabIndex.intValue == index) Color.White
                                    else Color.White.copy(alpha = 0.6f),
                                    fontSize = 16.sp,
                                    fontWeight = if (selectedTabIndex.intValue == index) FontWeight.Bold
                                    else FontWeight.Normal,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .fillMaxSize(),
                                    textAlign = TextAlign.Center,
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(2.dp)
                                        .background(
                                            if (selectedTabIndex.intValue == index) Color(0xFF3A3F47)
                                            else Color.Transparent
                                        )
                                )
                            }
                        }
                    }
                }
                when (selectedTabIndex.intValue) {
                    0 -> AboutMovieContent(movie)
                    1 -> CastContent(movie, movieViewModel)
                }
            }
        },
    )
}

