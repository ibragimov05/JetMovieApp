package uz.android.jetmovieapp.feature.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.android.jetmovieapp.common.models.CastResponse
import uz.android.jetmovieapp.common.network.MovieNetwork
import javax.inject.Inject

sealed class CastUiState {
    data object Loading : CastUiState()
    data class Success(val movies: CastResponse) : CastUiState()
    data class Error(val message: String) : CastUiState()
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieNT: MovieNetwork,
) : ViewModel() {
    private val _castState = MutableStateFlow<CastUiState>(CastUiState.Loading)
    val castState = _castState.asStateFlow()

    fun getMovieCasts(movieId: String) {
        viewModelScope.launch {
            _castState.value = CastUiState.Loading
            try {
                Log.d("MovieViewModel", "Fetching casts for movie ID: $movieId")
                val casts = movieNT.getMovieCredits(movieId)
                Log.d("MovieViewModel", "Successfully fetched casts: ${casts.cast.size} items")
                _castState.value = CastUiState.Success(casts)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching casts for movie ID: $movieId", e)
                _castState.value = CastUiState.Error("Error fetching cast: ${e.message}")
            }
        }
    }
}