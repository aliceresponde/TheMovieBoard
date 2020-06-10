package com.aliceresponde.themovieboard.data.repository

import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.local.Movie
import com.aliceresponde.themovieboard.data.remote.NoInternetException

interface MoviesRepository {
    @Throws(NoInternetException::class)
    suspend fun syncPopularMovies()


    @Throws(NoInternetException::class)
    suspend fun syncRatedMovies()

    fun getMovieByName(name: String): LiveData<List<Movie>>
    fun getPopularMovies(): LiveData<List<Movie>>
    fun getRatedMovies(): LiveData<List<Movie>>

    @Throws(NoInternetException::class)
    suspend fun getMovieVideo(movieId: Int): String

    @Throws(NoInternetException::class)
    suspend fun searchMoviesByName(name: String): LiveData<List<Movie>>

    suspend fun getMovieById(movieId: Int): Movie
}
