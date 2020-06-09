package com.aliceresponde.themovieboard.data.repository

import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.ui.model.ShowItem

interface MoviesRepository {
    @Throws(NoInternetException::class)
    suspend fun getPopularMovies(): LiveData<List<ShowItem>>
    @Throws(NoInternetException::class)
    suspend fun getRatedMovies(): LiveData<List<ShowItem>>
    @Throws(NoInternetException::class)
    suspend fun getMovieVideo(movieId: Int): String
    @Throws(NoInternetException::class)
    suspend fun searchMoviesByName(name: String): LiveData<List<ShowItem>>

    suspend fun getMovieById(movieId: Int): ShowItem
}
