package com.aliceresponde.themovieboard.data.repository

import com.aliceresponde.themovieboard.data.local.Movie
import com.aliceresponde.themovieboard.data.remote.NoInternetException

interface MoviesRepository {
    @Throws(NoInternetException::class)
    suspend fun fetchPopularMovies()

    @Throws(NoInternetException::class)
    suspend fun fetchRatedMovies()

    @Throws(NoInternetException::class)
    suspend fun fetchMoviesByName(name: String)

    @Throws(NoInternetException::class)
    suspend fun getMovieVideo(movieId: Int): String

    suspend fun getPopularMovies(): List<Movie>
    suspend fun getRatedMovies(): List<Movie>
    suspend fun getMovieByName(name: String): List<Movie>

}
