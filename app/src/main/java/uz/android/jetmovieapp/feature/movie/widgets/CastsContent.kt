package uz.android.jetmovieapp.feature.movie.widgets

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.constants.SpacerH24
import uz.android.jetmovieapp.common.models.Cast
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.feature.movie.CastUiState
import uz.android.jetmovieapp.feature.movie.MovieViewModel
import uz.android.jetmovieapp.R

@Composable
fun CastContent(movie: MovieResult, viewModel: MovieViewModel) {
    val castState = viewModel.castState.collectAsState()

    LaunchedEffect(movie.id) {
        Log.d("CastContent", "LaunchedEffect triggered for movie ID: ${movie.id}")
        viewModel.getMovieCasts(movie.id.toString())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val state = castState.value) {
            is CastUiState.Loading -> {
                CircularProgressIndicator(
                    color = Color.White, modifier = Modifier.align(Alignment.Center)
                )
            }

            is CastUiState.Success -> {
                if (state.movies.cast.isEmpty()) {
                    Text(
                        text = "No cast information available",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Column {
                        state.movies.cast.forEachIndexed { index, cast ->
                            CastItem(cast = cast)
                            SpacerH24()
                        }
                    }
                }
            }

            is CastUiState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.message, color = Color.Red, textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { viewModel.getMovieCasts(movie.id.toString()) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}

@Composable
private fun CastItem(cast: Cast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = Constants.IMAGES_BASE_URL + cast.profile_path,
            contentDescription = "Cast image",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.popcorn)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cast.name, style = TextStyle(
                    color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = cast.character, style = TextStyle(
                    color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp
                )
            )
        }
    }
}