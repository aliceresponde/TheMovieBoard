package com.aliceresponde.themovieboard.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Serie>)

    @Query("SELECT * FROM series WHERE  UPPER(name) LIKE '%' || UPPER(:value) || '%' limit 30")
    fun getSeriesByName(value: String) : LiveData<List<Serie>>
}