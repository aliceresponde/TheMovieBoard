package com.aliceresponde.themovieboard.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Movie>)

    @Query("SELECT * FROM movies WHERE  UPPER(title) LIKE '%' || UPPER(:value) || '%' limit 30")
    fun getMoviesByTitle(value: String) : LiveData<List<Movie>>
}