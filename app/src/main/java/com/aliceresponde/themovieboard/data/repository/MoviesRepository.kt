package com.aliceresponde.themovieboard.data.repository

import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.local.Movie
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.ui.model.ShowItem

interface MoviesRepository {
    @Throws(NoInternetException::class)
    suspend fun syncPopularMovies()
    @Throws(NoInternetException::class)
    suspend fun syncRatedMovies()
    @Throws(NoInternetException::class)
    suspend fun fetchMoviesByName( name: String)

    fun getPopularMovies(): LiveData<List<Movie>>
    fun getRatedMovies(): LiveData<List<Movie>>
    suspend fun getMovieByName(name: String): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie

    @Throws(NoInternetException::class)
    suspend fun getMovieVideo(movieId: Int): String


}
