package com.aliceresponde.themovieboard.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Serie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSerie(movie: Serie)

    @Query("SELECT * FROM series WHERE  UPPER(name) LIKE '%' || UPPER(:value) || '%' limit 30")
    fun getSeriesByName(value: String): LiveData<List<Serie>>

    @Query("SELECT * FROM series")
    fun getAllSeries(): LiveData<List<Serie>>

    @Query("SELECT * FROM series ORDER BY popularity DESC")
    fun getPopularSeries(): LiveData<List<Serie>>

    @Query("SELECT * FROM series ORDER BY vote_average DESC")
    fun getRatedSeries(): LiveData<List<Serie>>

    @Query(value = "SELECT COUNT(id) FROM series")
    fun countSeries(): Int

    @Query(value = "SELECT * FROM series WHERE id = :id")
    suspend fun getSerieById(id: Int): Serie

    @Query("UPDATE series SET videoKey = :video WHERE id = :id")
    fun updateVideo(id: Int, video: String): Int
}