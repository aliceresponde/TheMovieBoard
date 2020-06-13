package com.aliceresponde.themovieboard.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE  UPPER(title) LIKE UPPER(:value) || '%' ORDER BY title ASC  limit 30")
    suspend fun getMoviesByTitle(value: String): List<Movie>

    @Query("SELECT * FROM movies limit 30")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC limit 30")
    fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM movies ORDER BY vote_average DESC limit 30")
    fun getRatedMovies(): List<Movie>

    @Query(value = "SELECT COUNT(id) FROM movies ")
    fun countMovies(): Int

    @Query(value = "SELECT * FROM movies WHERE id = :id ")
    suspend fun getMovieById(id: Int): Movie

    @Query("UPDATE movies SET videoKey = :video WHERE id = :id")
    fun updateVideo(id: Int, video: String): Int
}