package com.aliceresponde.themovieboard.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Movie::class,
        Serie::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao(): MovieDao

    abstract fun getSerieDao(): SerieDao

    companion object {
        const val DATABASE_NAME = "movieboard.db"
    }
}