package uz.android.jetmovieapp.common.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.android.jetmovieapp.accessToken
import uz.android.jetmovieapp.common.constants.Constants.BASE_URL
import uz.android.jetmovieapp.common.data.FavDao
import uz.android.jetmovieapp.common.data.FavDatabase
import uz.android.jetmovieapp.common.network.MovieNetwork
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMovieNetwork(): MovieNetwork {
        val client = OkHttpClient.Builder().addInterceptor(AuthInterceptor(accessToken)).build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MovieNetwork::class.java)
    }

    @Singleton
    @Provides
    fun provideFavDao(favDatabase: FavDatabase): FavDao = favDatabase.favDao()

    @Singleton
    @Provides
    fun provideFavDatabase(@ApplicationContext context: Context): FavDatabase =
        Room.databaseBuilder(
            context,
            FavDatabase::class.java, "favorite_movies"
        ).fallbackToDestructiveMigration().build()
}

