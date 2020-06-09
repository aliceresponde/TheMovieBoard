package com.aliceresponde.themovieboard.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.aliceresponde.themovieboard.data.local.MovieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.data.remote.response.MoviesResponse
import com.aliceresponde.themovieboard.movietoShow
import com.aliceresponde.themovieboard.toMovieEntity
import com.aliceresponde.themovieboard.toShowItem
import com.aliceresponde.themovieboard.ui.model.ShowItem
import retrofit2.Response

typealias Videos = List<String>
typealias ShowItems = List<ShowItem>

class MoviesRepositoryImpl(
    private val movieDao: MovieDao,
    private val service: MoviesApi
) : MoviesRepository {

    override suspend fun getPopularMovies(): LiveData<ShowItems> {
        return try {
            val response = service.getPopularMovies()
            saveMoviesFromRemote(response)
            movieDao.getPopularMovies().movietoShow()
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            movieDao.getPopularMovies().movietoShow()
        }
    }

    override suspend fun getRatedMovies(): LiveData<ShowItems> {
        return try {
            val response = service.getTopRatedMovies()
            saveMoviesFromRemote(response)
            movieDao.getRatedMovies().movietoShow()
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            movieDao.getRatedMovies().movietoShow()
        }
    }

    override suspend fun getMovieVideo(movieId: Int): String {
        return try {
            val response = service.getMovieVideos(movieId)
            if (response.isSuccessful) {
                    response.body()?.results?.first()?.videoId ?: ""
            } else {""}
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            return ""
        }
    }

    override suspend fun searchMoviesByName(name: String): LiveData<ShowItems> {
        return try {
            val response = service.searchMovie(name)
            saveMoviesFromRemote(response)
            movieDao.getMoviesByTitle(name).movietoShow()
        } catch (e: NoInternetException) {
            Log.d("TAG", e.message)
            movieDao.getPopularMovies().movietoShow()
        }
    }

    override suspend fun getMovieById(movieId: Int): ShowItem {
        return movieDao.getMovieById(movieId).toShowItem()
    }

    private suspend fun saveMoviesFromRemote(response: Response<MoviesResponse>) {
        if (response.isSuccessful) {
            response.body()?.results?.run {
                map { it.toMovieEntity() }.also { movieDao.insertAll(it) }
            }
        }
    }

}