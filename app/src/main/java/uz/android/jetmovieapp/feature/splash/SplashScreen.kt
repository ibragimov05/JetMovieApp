package uz.android.jetmovieapp.feature.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import okhttp3.Route
import uz.android.jetmovieapp.R
import uz.android.jetmovieapp.common.navigation.Routes
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.feature.home.HomeViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val homeVM = hiltViewModel<HomeViewModel>()

    // Delays 2 seconds and navigates to the next screen
    LaunchedEffect(Unit) {
        val movies =  homeVM.getMovies(category = "popular");

        Log.d("MOVIES", "SplashScreen: $movies")

        delay(2000)
        navController.navigate(Routes.Home.name) {
            popUpTo(Routes.Splash.name) { inclusive = true }
        }
    }

    Scaffold(
        containerColor = AppColors.mainColor,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(160.dp),
                    painter = painterResource(id = R.drawable.popcorn),
                    contentDescription = "POPCORN",
                )
            }
        },
    )
}