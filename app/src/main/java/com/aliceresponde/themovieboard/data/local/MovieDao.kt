package com.aliceresponde.themovieboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE  UPPER(title) LIKE '%' || UPPER(:value) || '%' limit 30")
    fun getMoviesByTitle(value: String): List<Movie>

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    fun getRatedMovies(): List<Movie>

    @Query(value = "SELECT COUNT(id) FROM movies")
    fun countMovies(): Int

    @Query(value = "SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie

    @Query("UPDATE movies SET videoKey = :video WHERE id = :id")
    fun updateVideo(id: Int, video: String): Int
}