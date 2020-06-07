package com.aliceresponde.themovieboard.repository.local

import androidx.room.Database

@Database(entities = [Movie::class, Serie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase {
    abstract fun getMoviesDao(): MovieDao
    abstract fun getSerieDao(): SerieDao
}