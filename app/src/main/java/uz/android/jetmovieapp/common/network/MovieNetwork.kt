package uz.android.jetmovieapp.common.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.android.jetmovieapp.common.models.MovieResponse
import javax.inject.Singleton

@Singleton
interface MovieNetwork {
    @GET("movie/{category}?language=en-US")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
    ): MovieResponse
}