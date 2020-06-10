package com.aliceresponde.themovieboard.data.repository

import com.aliceresponde.themovieboard.data.local.MovieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.response.MoviesResponse
import com.aliceresponde.themovieboard.toMovieEntity
import com.aliceresponde.themovieboard.toShowItem
import com.aliceresponde.themovieboard.ui.model.ShowItem
import retrofit2.Response

class MoviesRepositoryImpl(
    private val movieDao: MovieDao,
    private val service: MoviesApi
) : MoviesRepository {

    override suspend fun getPopularMovies(): List<ShowItem> {
        val response = service.getPopularMovies()
        saveMoviesFromRemote(response)
        return movieDao.getPopularMovies().map { it.toShowItem() }
    }

    override suspend fun getRatedMovies(): List<ShowItem> {
        val response = service.getTopRatedMovies()
        saveMoviesFromRemote(response)
        return movieDao.getRatedMovies().map { it.toShowItem() }
    }

    override suspend fun getMovieVideo(movieId: Int): String {
        val response = service.getMovieVideos(movieId)
        var videoKey = ""
        return if (response.isSuccessful) {
            response.body()?.id
            videoKey = response.body()?.let {
                it.results.first().videoId
            } ?: ""
            movieDao.updateVideo(movieId, videoKey)
            videoKey
        } else {
            videoKey
        }
    }

    override suspend fun searchMoviesByName(name: String): List<ShowItem> {
        val response = service.searchMovie(name)
        saveMoviesFromRemote(response)
        return movieDao.getMoviesByTitle(name).map { it.toShowItem() }
    }

    override suspend fun getMovieById(movieId: Int): ShowItem {
        return movieDao.getMovieById(movieId).toShowItem()
    }

    private fun saveMoviesFromRemote(response: Response<MoviesResponse>) {
        if (response.isSuccessful) {
            response.body()?.movies?.run {
                map { it.toMovieEntity() }.also { movieDao.insertAll(it) }
            }
        }
    }

}