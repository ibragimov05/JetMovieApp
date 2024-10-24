package uz.android.jetmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import uz.android.jetmovieapp.common.navigation.AppRouter
import uz.android.jetmovieapp.common.theme.JetMovieAppTheme

/**
 * Creating Android Jetpack compose project step by step:
 * 1. Create project
 * 2. Add dependencies
 * 3. Create Application in root folder. For example: WeatherApplication.kt
 * 4. Add WeatherApplication.kt to manifest file
 * 5. Create di: AppModule
 * 6. Create navigation
 * 7. Create model
 * 8. Create network
 * 9. Add network retrofit
 * 10. Add retrofit to di
 * 11. Create data or exception
 * 12. Create repository
 * 13. Create viewmodel
 */
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
