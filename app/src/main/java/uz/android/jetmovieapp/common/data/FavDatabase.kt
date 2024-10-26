package uz.android.jetmovieapp.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.android.jetmovieapp.common.models.MovieRoomResult

@Database(
    entities = [MovieRoomResult::class], // Remove Unit::class
    version = 2,
    exportSchema = false
)
abstract class FavDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao
}