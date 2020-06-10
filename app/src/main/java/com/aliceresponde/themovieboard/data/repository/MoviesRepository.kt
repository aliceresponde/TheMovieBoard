package com.aliceresponde.themovieboard.data.repository

import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.ui.model.ShowItem

interface MoviesRepository {
    @Throws(NoInternetException::class)
    suspend fun getPopularMovies(): List<ShowItem>

    @Throws(NoInternetException::class)
    suspend fun getRatedMovies(): List<ShowItem>

    @Throws(NoInternetException::class)
    suspend fun getMovieVideo(movieId: Int): String

    @Throws(NoInternetException::class)
    suspend fun searchMoviesByName(name: String): List<ShowItem>

    suspend fun getMovieById(movieId: Int): ShowItem
}
