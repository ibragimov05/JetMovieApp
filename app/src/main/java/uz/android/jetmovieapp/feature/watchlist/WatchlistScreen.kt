package uz.android.jetmovieapp.feature.watchlist

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun WatchlistScreen(navController: NavController) {
    Surface {
        Text(
            "Watchlist Screen",
            style = MaterialTheme.typography.titleLarge
        )  // TODO: Implement the watchlist screen content here
    }
}