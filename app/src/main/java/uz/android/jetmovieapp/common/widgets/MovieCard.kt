package uz.android.jetmovieapp.common.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.common.theme.AppColors

@Composable
 fun MovieCard(
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
                        text = "%.1f".format(movie.vote_average) , style = TextStyle(
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