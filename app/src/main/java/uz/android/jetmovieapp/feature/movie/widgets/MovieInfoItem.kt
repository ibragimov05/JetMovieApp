package uz.android.jetmovieapp.feature.movie.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MovieInfoItem(
    icon: ImageVector,
    text: String,
) {
    val color = Color(0xFF92929D)

    Row {
        Icon(icon, contentDescription = text, tint = color)

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = text, style = TextStyle(
                color = color,
                fontWeight = FontWeight.W500,
            )
        )
    }
}