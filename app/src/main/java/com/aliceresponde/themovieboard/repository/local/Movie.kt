package com.aliceresponde.themovieboard.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movies")
data class Movie (
    @PrimaryKey
    val id : Integer,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val imageUrl: String
)