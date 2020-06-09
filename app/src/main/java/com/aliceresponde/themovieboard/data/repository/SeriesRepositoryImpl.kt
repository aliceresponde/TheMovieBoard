package com.aliceresponde.themovieboard.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.local.SerieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.data.remote.response.MoviesResponse
import com.aliceresponde.themovieboard.serietoShow
import com.aliceresponde.themovieboard.toSerieEntity
import com.aliceresponde.themovieboard.toShowItem
import com.aliceresponde.themovieboard.ui.model.ShowItem
import retrofit2.Response

typealias SeriesItems = List<ShowItem>

class SeriesRepositoryImpl(
    private val serieDao: SerieDao,
    private val service: MoviesApi
) : SeriesRepository {

    override suspend fun getPopularSeries(): LiveData<ShowItems> {
        return try {
            val response = service.getPopularSerie()
            saveMoviesFromRemote(response)
            serieDao.getPopularSeries().serietoShow()
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            serieDao.getPopularSeries().serietoShow()
        }
    }

    override suspend fun getRatedSeries(): LiveData<ShowItems> {
        return try {
            val response = service.getTopRatedSeries()
            saveMoviesFromRemote(response)
            serieDao.getRatedSeries().serietoShow()
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            serieDao.getRatedSeries().serietoShow()
        }
    }

    override suspend fun getSerieVideo(serieId: Int): String {
        return try {
            val response = service.getSerieVideos(serieId)
            if (response.isSuccessful) {
                response.body()?.results?.first()?.videoId ?: ""
            } else {
                ""
            }
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            return ""
        }
    }

    override suspend fun searchSerieByName(name: String): LiveData<ShowItems> {
        return try {
            val response = service.searchSerie(name)
            saveMoviesFromRemote(response)
            serieDao.getSeriesByName(name).serietoShow()
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            serieDao.getSeriesByName(name).serietoShow()
        }
    }

    override suspend fun getSerieById(serieId: Int): ShowItem {
        return serieDao.getSerieById(serieId).toShowItem()
    }

    private suspend fun saveMoviesFromRemote(response: Response<MoviesResponse>) {
        if (response.isSuccessful) {
            response.body()?.results?.run {
                map { it.toSerieEntity() }.also {
                    serieDao.insertAll(it)
                }
            }
        }
    }

}