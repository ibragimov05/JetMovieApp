package uz.android.jetmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.android.jetmovieapp.common.navigation.AppRouter
import uz.android.jetmovieapp.common.theme.JetMovieAppTheme
import uz.android.jetmovieapp.feature.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetMovieAppTheme(content = { AppRouter() })
        }
    }
}
