package uz.android.jetmovieapp.feature.movie.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.android.jetmovieapp.common.models.MovieResult

@Composable
 fun AboutMovieContent(movie: MovieResult) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = movie.overview,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            lineHeight = 24.sp
        )
    }
}

