package uz.android.jetmovieapp.common.di

import coil.intercept.Interceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.android.jetmovieapp.accessToken
import uz.android.jetmovieapp.common.constants.Constants.BASE_URL
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
}

