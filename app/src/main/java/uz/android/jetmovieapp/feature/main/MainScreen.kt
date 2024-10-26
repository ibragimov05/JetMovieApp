package uz.android.jetmovieapp.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.android.jetmovieapp.R
import uz.android.jetmovieapp.common.models.TabBoxModel
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.feature.home.HomeScreen
import uz.android.jetmovieapp.feature.home.HomeViewModel
import uz.android.jetmovieapp.feature.search.SearchScreen
import uz.android.jetmovieapp.feature.watchlist.WatchlistScreen

@Composable
fun MainScreen(navController: NavController, homeVM: HomeViewModel) {
    val mainVM = hiltViewModel<MainViewModel>()

    val tabBoxValues = listOf(
        TabBoxModel(title = "Home", iconId = R.drawable.home),
        TabBoxModel(title = "Search", iconId = R.drawable.search),
        TabBoxModel(title = "Watch list", iconId = R.drawable.save),
    )
    var navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    LaunchedEffect(Unit) {
        homeVM.getAllMoviesMovies()
    }

    Scaffold(
        containerColor = AppColors.mainColor,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)

            ) {
                when (mainVM.currentIndex.intValue) {
                    0 -> {
                        HomeScreen(navController, homeVM)
                    }

                    1 -> {
                        SearchScreen(navController, homeVM)
                    }

                    2 -> {
                        WatchlistScreen(navController)
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = AppColors.mainBlueColor,
                            strokeWidth = 2f,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp + navigationBarHeight)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    tabBoxValues.forEachIndexed { index, item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            IconButton(
                                onClick = {
                                    mainVM.currentIndex.intValue = index
                                },
                            ) {
                                Image(
                                    painter = painterResource(id = item.iconId),
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(
                                        color = if (mainVM.currentIndex.intValue == index) AppColors.mainBlueColor
                                        else AppColors.grey
                                    )
                                )
                            }
                            Text(
                                text = item.title, style = TextStyle(
                                    color = if (mainVM.currentIndex.intValue == index) AppColors.mainBlueColor
                                    else AppColors.grey
                                ), fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        },
    )
}