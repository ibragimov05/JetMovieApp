package uz.android.jetmovieapp.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.android.jetmovieapp.common.constants.Constants.categories
import uz.android.jetmovieapp.common.models.AllMovies
import uz.android.jetmovieapp.common.models.MovieResponse
import uz.android.jetmovieapp.common.models.MovieResult
import uz.android.jetmovieapp.common.network.MovieNetwork
import javax.inject.Inject

sealed class MoviesUiState {
    data object Loading : MoviesUiState()
    data class Success(val movies: AllMovies) : MoviesUiState()
    data class Error(val message: String) : MoviesUiState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieNT: MovieNetwork,
) : ViewModel() {
    private val _moviesState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val moviesState = _moviesState.asStateFlow()

    fun getAllMoviesMovies() {
        viewModelScope.launch {
            _moviesState.value = MoviesUiState.Loading
            var results = mutableListOf<MovieResponse>()

            for (i in 0..2) {
                try {
                    val res = movieNT.getMovies(category = categories[i])
                    results.add(res)
                } catch (_: Exception) {
                    continue
                }
            }

            _moviesState.value = MoviesUiState.Success(
                AllMovies(
                    popular = results[0],
                    topRated = results[1],
                    nowPlaying = results[2],
                )
            )
        }
    }

    fun getMovieById(movieId: String): MovieResult? {
        Log.d("getMovieById", "getMovieById: ${_moviesState.value}")
        if (_moviesState.value is MoviesUiState.Success) {
            Log.d(
                "getMovieById",
                "getMovieById: getMovieByIdgetMovieByIdgetMovieByIdgetMovieByIdgetMovieById"
            )
            val movies = (_moviesState.value as MoviesUiState.Success).movies
            val allMovies =
                movies.popular.results + movies.topRated.results + movies.nowPlaying.results

            for (movie in allMovies) {
                if (movie.id.toString() == movieId) {
                    return movie
                }
            }
        }
        return null
    }

}