package uz.android.jetmovieapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.android.jetmovieapp.feature.home.HomeScreen
import uz.android.jetmovieapp.feature.movie.MovieScreen
import uz.android.jetmovieapp.feature.splash.SplashScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.name) {
        composable(Routes.Splash.name) {
            SplashScreen()
        }
        composable(Routes.Home.name) {
            HomeScreen()
        }
        composable(Routes.MovieDetails.name) {
            MovieScreen()
        }
        // Add more routes here...
    }
}