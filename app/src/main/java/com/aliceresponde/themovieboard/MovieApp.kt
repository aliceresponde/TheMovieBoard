package com.aliceresponde.themovieboard

import android.app.Application
import com.aliceresponde.themovieboard.data.local.MoviesDatabase
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.NetworkConnectionInterceptor
import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import com.aliceresponde.themovieboard.data.repository.MoviesRepositoryImpl
import com.aliceresponde.themovieboard.data.repository.SeriesRepository
import com.aliceresponde.themovieboard.data.repository.SeriesRepositoryImpl
import com.aliceresponde.themovieboard.di.component.MoviesBoardComponent

class MovieApp : Application() {
    private lateinit var component: MoviesBoardComponent
        private set

    lateinit var database: MoviesDatabase
        private set
    lateinit var service: MoviesApi
        private set
    val moviesRepository: MoviesRepository by lazy {
        MoviesRepositoryImpl(
            database.getMoviesDao(),
            service
        )
    }
    val seriesRepository: SeriesRepository by lazy {
        SeriesRepositoryImpl(
            database.getSerieDao(),
            service
        )
    }

    override fun onCreate() {
        super.onCreate()
        database = MoviesDatabase.invoke(this)
        service = MoviesApi.invoke(NetworkConnectionInterceptor(this))
//      component = DaggerMoviesBoardComponent.factory().create(this)
    }

    fun provideMoviesRepository() = moviesRepository
    fun provideSeriesRepository() = seriesRepository
}