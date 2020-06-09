package com.aliceresponde.themovieboard.di.module.data

import com.aliceresponde.themovieboard.data.local.MovieDao
import com.aliceresponde.themovieboard.data.local.SerieDao
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.repository.MoviesRepositoryImpl
import com.aliceresponde.themovieboard.data.repository.SeriesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
interface RepositoryImplementationModule {
    @Provides
    fun provideMoviesRepositoryImpl(
        movieDao: MovieDao,
        service: MoviesApi
    ) = MoviesRepositoryImpl(movieDao, service)

    @Provides
    fun provideSeriesRepositortImpl(
        serieDao: SerieDao,
        service: MoviesApi
    ) = SeriesRepositoryImpl(serieDao, service)
}
