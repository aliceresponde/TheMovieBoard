package com.aliceresponde.themovieboard.data.repository

import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.ui.model.ShowItem

interface SeriesRepository {
    @Throws(NoInternetException::class)
    suspend fun getPopularSeries(): LiveData<List<ShowItem>>
    @Throws(NoInternetException::class)
    suspend fun getRatedSeries(): LiveData<List<ShowItem>>
    @Throws(NoInternetException::class)
    suspend fun getSerieVideo(serieId: Int): String
    @Throws(NoInternetException::class)
    suspend fun searchSerieByName(name: String): LiveData<List<ShowItem>>

    suspend fun getSerieById(serieId: Int): ShowItem
}