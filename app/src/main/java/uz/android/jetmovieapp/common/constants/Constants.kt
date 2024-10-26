package uz.android.jetmovieapp.common.constants


object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500/"
//    const val upcomingRemainingUrl = "discover/movie?include_adult=false&include_video=false&language=en-US&"

    // movie category
    const val NOW_PLAYING = "now_playing"
    const val POPULAR = "popular"
    const val TOP_RATED = "top_rated"
    const val UPCOMING = "discover"

    val categories = listOf(
        POPULAR,
        TOP_RATED,
        NOW_PLAYING,
        UPCOMING,
    )

    val movieCategories = listOf(
        "Now playing",
        "Top rated",
        "Popular"
    )
}