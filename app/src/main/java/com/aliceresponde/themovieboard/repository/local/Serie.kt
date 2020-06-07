package com.aliceresponde.themovieboard.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Series")
data class Serie (
    @PrimaryKey
    val id : Integer,
    val name: String,
    val overview: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("poster_path")
    val imageUrl: String
)