package com.aliceresponde.themovieboard.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Series")
data class Serie(
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    @ColumnInfo(name="first_air_date")
    val date: String,
    @ColumnInfo(name="poster_path")
    val imageUrl: String,
    @ColumnInfo(name="vote_average")
    val voteAverage: Float,
    val popularity: Float,
    val videoKey: String =""
)