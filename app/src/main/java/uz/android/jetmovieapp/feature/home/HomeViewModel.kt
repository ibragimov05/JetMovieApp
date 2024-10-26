package uz.android.jetmovieapp.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.android.jetmovieapp.common.network.MovieNetwork
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieNT: MovieNetwork) : ViewModel() {
    suspend fun getMovies(category: String) = movieNT.getMovies(
        page = 1, category = category
    )
}