package com.aliceresponde.themovieboard.data.repository

import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.local.Movie
import com.aliceresponde.themovieboard.data.local.MovieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.response.MoviesResponse
import com.aliceresponde.themovieboard.toMovieEntity
import retrofit2.Response

// FIXME: 10/06/20  user dataSouce to decouple repository
class MoviesRepositoryImpl(
    private val movieDao: MovieDao,
    private val service: MoviesApi
) : MoviesRepository {
    override suspend fun fetchPopularMovies() {
        val response = service.getPopularMovies()
        saveMoviesFromRemote(response)
    }

    override suspend fun fetchRatedMovies() {
        val response = service.getTopRatedMovies()
        saveMoviesFromRemote(response)
    }

    override suspend fun fetchMoviesByName(name: String) {
        val response = service.searchMovieByName(name)
        saveMoviesFromRemote(response)
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return movieDao.getPopularMovies()
    }

    override suspend fun getRatedMovies(): List<Movie> {
        return movieDao.getRatedMovies()
    }

    override suspend fun getMovieByName(name: String): List<Movie> {
        return movieDao.getMoviesByTitle(name)
    }

    override suspend fun getMovieVideo(movieId: Int): String {
        val response = service.getMovieVideos(movieId)
        var videoKey = ""
        return if (response.isSuccessful) {
            response.body()?.id
            videoKey = response.body()?.results?.first()?.videoId ?: ""
            movieDao.updateVideo(movieId, videoKey)
            videoKey
        } else {
            videoKey
        }
    }

    private suspend fun saveMoviesFromRemote(response: Response<MoviesResponse>) {
        if (response.isSuccessful) {
            response.body()?.movies?.run {
                map { it.toMovieEntity() }.also { movieDao.insertAll(it) }
            }
        }
    }
}