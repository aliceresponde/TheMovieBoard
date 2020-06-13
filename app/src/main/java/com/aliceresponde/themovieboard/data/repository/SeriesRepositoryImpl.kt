package com.aliceresponde.themovieboard.data.repository

import com.aliceresponde.themovieboard.data.local.Serie
import com.aliceresponde.themovieboard.data.local.SerieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.response.MovieVideoResult
import com.aliceresponde.themovieboard.data.remote.response.SerieResponse
import com.aliceresponde.themovieboard.data.remote.response.SerieVideoResponse
import com.aliceresponde.themovieboard.data.remote.response.SerieVideoResult
import com.aliceresponde.themovieboard.toSerieEntity
import retrofit2.Response

class SeriesRepositoryImpl(
    private val serieDao: SerieDao,
    private val service: MoviesApi
) : SeriesRepository {
    override suspend fun fetchPopularSerie() {
        val response = service.getPopularSerie()
        saveSerieFromRemote(response)    }

    override suspend fun fetchRatedMovies() {
        val response = service.getTopRatedSeries()
        saveSerieFromRemote(response)    }

    override suspend fun fetchSerieByName(name: String) {
        val response = service.searchSerieByName(name)
        saveSerieFromRemote(response)
    }

    override suspend fun getSerieVideo(serieId: Int): List<SerieVideoResult> {
        val response = service.getSerieVideos(serieId)
        val videoList = response.body()?.serieVideos
        return videoList?.let { it } ?: listOf()
    }

    override suspend fun getPopularSerie(): List<Serie> {
        return serieDao.getPopularSeries()
    }

    override suspend fun getRatedSerie(): List<Serie> {
        return  serieDao.getRatedSeries()
    }

    override suspend fun getSerieByName(name: String): List<Serie> {
        return serieDao.getSeriesByName(name)
    }

    private  fun saveSerieFromRemote(response: Response<SerieResponse>) {
        if (response.isSuccessful) {
            response.body()?.series?.run {
                map { it.toSerieEntity() }.also { serieDao.insertAll(it) }
            }
        }
    }

}