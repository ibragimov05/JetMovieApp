package uz.android.jetmovieapp.feature.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.common.navigation.Routes
import uz.android.jetmovieapp.common.theme.AppColors

@Composable
fun PopularMovieWidget(
    navController: NavController,
    movie: MovieResult,
    index: Int,
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 24.dp)
            .height(210.dp)
            .width(145.dp)
            .clickable {
                navController.navigate(Routes.MovieDetails.name + "/${movie.id}")
            }, shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {
            Image(
                painter = rememberAsyncImagePainter(Constants.IMAGES_BASE_URL + movie.poster_path),
                contentDescription = "Movie poster",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
            )

            Box {
                val text = "${index + 1}"
                val borderColor = AppColors.mainBlueColor
                val textColor = AppColors.mainColor
                val outlineOffset = 1.dp
                val testSize = 70.sp

                // Outline Texts for Border
                Text(
                    text = text, style = TextStyle(
                        color = borderColor, fontSize = testSize
                    ), modifier = Modifier.offset(-outlineOffset, -outlineOffset)
                )

                Text(
                    text = text, style = TextStyle(
                        color = borderColor, fontSize = testSize
                    ), modifier = Modifier.offset(-outlineOffset, outlineOffset)
                )
                Text(
                    text = text, style = TextStyle(
                        color = borderColor, fontSize = testSize
                    ), modifier = Modifier.offset(outlineOffset, outlineOffset)
                )

                // Main Text with Foreground Color
                Text(
                    text = text, style = TextStyle(
                        color = textColor, fontSize = testSize
                    )
                )
            }
        }
    }
}