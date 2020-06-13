package com.aliceresponde.themovieboard.data.remote

import com.aliceresponde.themovieboard.BuildConfig
import com.aliceresponde.themovieboard.data.remote.response.MovieVideosResponse
import com.aliceresponde.themovieboard.data.remote.response.MoviesResponse
import com.aliceresponde.themovieboard.data.remote.response.SerieResponse
import com.aliceresponde.themovieboard.data.remote.response.SerieVideoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val API_KEY = BuildConfig.API_KEY
const val BASE_URL = BuildConfig.BASE_URL

interface MoviesApi {
    // https://api.themoviedb.org/3/movie/419704/videos?api_key=dc444e2fa09525a80a60d5db7ff1bb07
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") id: Int): Response<MovieVideosResponse>

    //https://api.themoviedb.org/3/search/movie?api_key=dc444e2fa09525a80a60d5db7ff1bb07&query=Lion
    @GET("search/movie")
    suspend fun searchMovieByName(@Query("query") keyWord: String): Response<MoviesResponse>

    // https://api.themoviedb.org/3/movie/popular?api_key=dc444e2fa09525a80a60d5db7ff1bb07
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    // https://api.themoviedb.org/3/movie/top_rated?api_key=dc444e2fa09525a80a60d5db7ff1bb07
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MoviesResponse>

//    ======================================== Serie ========================================

    // https://api.themoviedb.org/3/tv/60735/videos?api_key=dc444e2fa09525a80a60d5db7ff1bb07
    @GET("tv/{tv_id}/videos")
    suspend fun getSerieVideos(@Path("tv_id") id: Int): Response<SerieVideoResponse>

    //https://api.themoviedb.org/3/search/tv?api_key=dc444e2fa09525a80a60d5db7ff1bb07&query=xena
    @GET("search/tv")
    suspend fun searchSerieByName(@Query("query") keyWord: String): Response<SerieResponse>

    // https://api.themoviedb.org/3/tv/popular?api_key=dc444e2fa09525a80a60d5db7ff1bb07
    @GET("tv/popular")
    suspend fun getPopularSerie(): Response<SerieResponse>

    // https://api.themoviedb.org/3/tv/top_rated?api_key=dc444e2fa09525a80a60d5db7ff1bb07
    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(): Response<SerieResponse>

    companion object {
        operator fun invoke(interceptor: NetworkConnectionInterceptor): MoviesApi {

            val logInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}