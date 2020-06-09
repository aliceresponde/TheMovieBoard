package com.aliceresponde.themovieboard.di.module.data

import android.content.Context
import androidx.room.Room
import com.aliceresponde.themovieboard.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): MoviesDatabase =
        Room.databaseBuilder(
            context,
            MoviesDatabase::class.java, MoviesDatabase.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideMovieDao(database: MoviesDatabase) = database.getMoviesDao()

    @Singleton
    @Provides
    fun provideSerieDao(database: MoviesDatabase) = database.getSerieDao()
}
