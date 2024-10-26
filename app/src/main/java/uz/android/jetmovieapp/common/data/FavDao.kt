package uz.android.jetmovieapp.common.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.android.jetmovieapp.common.models.MovieRoomResult

@Dao
interface FavDao {
    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovies(): List<MovieRoomResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovie(movie: MovieRoomResult)

    @Delete
    suspend fun deleteFavMovie(movie: MovieRoomResult)
}