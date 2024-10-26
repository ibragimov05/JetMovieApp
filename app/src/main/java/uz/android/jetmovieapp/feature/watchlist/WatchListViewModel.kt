package uz.android.jetmovieapp.feature.watchlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.android.jetmovieapp.common.data.FavDao
import uz.android.jetmovieapp.common.models.MovieRoomResult
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val favDao: FavDao,
) : ViewModel() {
    private val _favList = MutableStateFlow<List<MovieRoomResult>>(emptyList())
    val favList = _favList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)

    init {
        loadFavoriteMovies()
    }

    fun isFavorite(movieId: Long): Boolean {
        return _favList.value.any { it.id == movieId }
    }

    fun toggleFavorite(movie: MovieRoomResult) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite(movie.id)) {
                removeFromFavorites(movie)
            } else {
                addToFavorites(movie)
            }
        }
    }

    private fun loadFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val movies = favDao.getFavoriteMovies()
                _favList.value = movies
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToFavorites(movie: MovieRoomResult) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                favDao.insertFavMovie(movie)
                // Reload the list after adding
                loadFavoriteMovies()
            } catch (e: Exception) {
                Log.d("ERROR", "addToFavorites: ${e.message}")
            }
        }
    }

    fun removeFromFavorites(movie: MovieRoomResult) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                favDao.deleteFavMovie(movie)
                // Reload the list after removing
                loadFavoriteMovies()
            } catch (e: Exception) {
                Log.d("ERROR", "addToFavorites: ${e.message}")
            }
        }
    }
}