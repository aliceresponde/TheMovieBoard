package com.aliceresponde.themovieboard.data.repository

import com.aliceresponde.themovieboard.data.local.SerieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.response.SerieResponse
import com.aliceresponde.themovieboard.toSerieEntity
import com.aliceresponde.themovieboard.toShowItem
import com.aliceresponde.themovieboard.ui.model.ShowItem
import retrofit2.Response

class SeriesRepositoryImpl(
    private val serieDao: SerieDao,
    private val service: MoviesApi
) : SeriesRepository {

    override suspend fun getPopularSeries(): List<ShowItem> {
        val response = service.getPopularSerie()
        saveSeriesFromRemote(response)
        return serieDao.getPopularSeries().map { it.toShowItem() }
    }

    override suspend fun getRatedSeries(): List<ShowItem> {
        val response = service.getTopRatedSeries()
        saveSeriesFromRemote(response)
        return serieDao.getRatedSeries().map { it.toShowItem() }
    }

    override suspend fun getSerieVideo(serieId: Int): String {
        val response = service.getSerieVideos(serieId)
        var videoKey = ""
        return if (response.isSuccessful) {
            response.body()?.id
            videoKey = response.body()?.let {
                it.results.first().videoId
            } ?: ""
            serieDao.updateVideo(serieId, videoKey)
            videoKey
        } else {
            videoKey
        }
    }

    override suspend fun searchSerieByName(name: String): List<ShowItem> {
        val response = service.searchSerie(name)
        saveSeriesFromRemote(response)
        return serieDao.getSeriesByName(name).map { it.toShowItem() }
    }


    override suspend fun getSerieById(serieId: Int): ShowItem {
        return serieDao.getSerieById(serieId).toShowItem()
    }

    private fun saveSeriesFromRemote(response: Response<SerieResponse>) {
        if (response.isSuccessful) {
            response.body()?.series?.run {
                map { it.toSerieEntity() }.also { serieDao.insertAll(it) }
            }
        }
    }
}