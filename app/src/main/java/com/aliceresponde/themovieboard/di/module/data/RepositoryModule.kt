package com.aliceresponde.themovieboard.di.module.data

import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import com.aliceresponde.themovieboard.data.repository.MoviesRepositoryImpl
import com.aliceresponde.themovieboard.data.repository.SeriesRepository
import com.aliceresponde.themovieboard.data.repository.SeriesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryImplementationModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun providesMoviesRepository(implementation: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun providesSeriesRepository(implementation: SeriesRepositoryImpl): SeriesRepository
}
