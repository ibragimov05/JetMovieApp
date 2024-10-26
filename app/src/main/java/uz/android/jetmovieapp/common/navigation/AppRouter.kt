package uz.android.jetmovieapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uz.android.jetmovieapp.feature.home.HomeScreen
import uz.android.jetmovieapp.feature.home.HomeViewModel
import uz.android.jetmovieapp.feature.main.MainScreen
import uz.android.jetmovieapp.feature.movie.MovieScreen
import uz.android.jetmovieapp.feature.movie.MovieViewModel
import uz.android.jetmovieapp.feature.splash.SplashScreen
import uz.android.jetmovieapp.feature.watchlist.WatchListViewModel

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    val homeVM = hiltViewModel<HomeViewModel>()
    val movieVM = hiltViewModel<MovieViewModel>()
    val watchListVM = hiltViewModel<WatchListViewModel>()

    NavHost(navController = navController, startDestination = Routes.Main.name) {
        composable(Routes.Splash.name) {
            SplashScreen(navController)
        }
        composable(Routes.Main.name) {
            MainScreen(navController, homeVM, watchListVM)
        }
        composable(Routes.Home.name) {
            HomeScreen(navController, homeVM)
        }
        val routeMD = Routes.MovieDetails.name
        composable(
            "$routeMD/{movieId}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                },
            ),
        ) { navBack ->
            navBack.arguments?.getString("movieId").let {
                MovieScreen(
                    navController, it ?: "", homeVM, movieVM, watchListVM,
                )
            }
        }
        // TODO: Add more routes here...
    }
}