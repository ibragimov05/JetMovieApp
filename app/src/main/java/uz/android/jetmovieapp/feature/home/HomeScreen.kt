package uz.android.jetmovieapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.android.jetmovieapp.common.constants.Constants
import uz.android.jetmovieapp.common.constants.SpacerH24
import uz.android.jetmovieapp.common.constants.paddingH16
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.common.widgets.SearchField
import uz.android.jetmovieapp.feature.home.widgets.CategoryMovieContent
import uz.android.jetmovieapp.feature.home.widgets.PopularMovieWidget
import uz.android.jetmovieapp.feature.main.MainViewModel

@Composable
fun HomeScreen(navController: NavController, homeVM: HomeViewModel) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val mainVM = hiltViewModel<MainViewModel>()
    val moviesState = homeVM.moviesState.collectAsState()
    var selectedTabIndex = remember { mutableIntStateOf(0) }
    val tabs = Constants.movieCategories

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.mainColor,
    ) {
        Column(
            modifier = Modifier
                .padding(PaddingValues(top = statusBarHeight, bottom = 0.dp))
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),

            ) {
            Column(modifier = paddingH16) {
                Text(
                    "What do you want to watch?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        color = AppColors.white,
                    ),
                )
                SpacerH24()
                SearchField(
                    enabled = false,
                    onTap = { mainVM.updateIndex(1) },
                    valueState = null,
                    labelId = "Search"
                )
            }

            when (val state = moviesState.value) {
                is MoviesUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = AppColors.mainBlueColor)
                    }
                }

                is MoviesUiState.Success -> {
                    val popularMovies = state.movies.popular.results
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        for (i in 0..9) {
                            PopularMovieWidget(
                                navController = navController, movie = popularMovies[i], index = i
                            )
                        }
                    }

                    SpacerH24()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            tabs.forEachIndexed { index, title ->
                                Column(modifier = Modifier
                                    .weight(1f)
                                    .clickable { selectedTabIndex.intValue = index }) {
                                    Text(
                                        text = title,
                                        color = if (selectedTabIndex.intValue == index) Color.White
                                        else Color.White.copy(alpha = 0.6f),
                                        fontSize = 16.sp,
                                        fontWeight = if (selectedTabIndex.intValue == index) FontWeight.Bold
                                        else FontWeight.Normal,
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                            .fillMaxSize(),
                                        textAlign = TextAlign.Center,
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(2.dp)
                                            .background(
                                                if (selectedTabIndex.intValue == index) AppColors.white
                                                else Color.Transparent
                                            )
                                    )
                                }
                            }
                        }
                    }
                    when (selectedTabIndex.intValue) {
                        0 -> CategoryMovieContent(
                            navController = navController, movies = state.movies.nowPlaying
                        )

                        1 -> CategoryMovieContent(
                            navController = navController, movies = state.movies.topRated
                        )

                        2 -> CategoryMovieContent(
                            navController = navController, movies = state.movies.popular
                        )
                    }
                }

                is MoviesUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.message, color = Color.Red, style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }
}