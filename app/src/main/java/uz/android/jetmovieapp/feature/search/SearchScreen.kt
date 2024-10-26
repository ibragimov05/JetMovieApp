package uz.android.jetmovieapp.feature.search

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    Surface {
        Text(
            "Search Screen",
            style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight.Bold,
            ),
        )
    }
}