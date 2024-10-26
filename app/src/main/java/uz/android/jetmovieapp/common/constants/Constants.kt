package uz.android.jetmovieapp.common.constants

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.ui.graphics.Color


object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500/"
//    const val upcomingRemainingUrl = "discover/movie?include_adult=false&include_video=false&language=en-US&"

    // movie category
    const val nowPlaying = "now_playing"
    const val popular = "popular"
    const val topRated = "top_rated"
    const val upcoming = "discover"

    val categories = listOf(
        upcoming,
        popular,
        topRated,
        nowPlaying
    )

    val movieCategories = listOf(
        "Now playing",
        "Upcoming",
        "Top rated",
        "Popular"
    )

    // tab box labels
    val tabBoxLabels = listOf(
        "Home",
        "Search",
        "Watch list"
    )

//    var allGenres = mutableListOf<Genre>()
}