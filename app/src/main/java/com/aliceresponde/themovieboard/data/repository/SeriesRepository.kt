package com.aliceresponde.themovieboard.data.repository

import com.aliceresponde.themovieboard.data.local.Serie
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.data.remote.response.SerieVideoResult

interface SeriesRepository {
    @Throws(NoInternetException::class)
    suspend fun fetchPopularSerie()

    @Throws(NoInternetException::class)
    suspend fun fetchRatedMovies()

    @Throws(NoInternetException::class)
    suspend fun fetchSerieByName(name: String)

    @Throws(NoInternetException::class)
    suspend fun getSerieVideo(serieId: Int): List<SerieVideoResult>

    suspend fun getPopularSerie(): List<Serie>
    suspend fun getRatedSerie(): List<Serie>
    suspend fun getSerieByName(name: String): List<Serie>
}
