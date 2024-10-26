package uz.android.jetmovieapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.android.jetmovieapp.feature.home.HomeScreen
import uz.android.jetmovieapp.feature.main.MainScreen
import uz.android.jetmovieapp.feature.movie.MovieScreen
import uz.android.jetmovieapp.feature.splash.SplashScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.name) {
        composable(Routes.Splash.name) {
            SplashScreen(navController)
        }
        composable(Routes.Main.name) {
            MainScreen(navController)
        }
        composable(Routes.Home.name) {
            HomeScreen(navController)
        }
        composable(Routes.MovieDetails.name) {
            MovieScreen(navController)
        }
        // Add more routes here...
    }
}