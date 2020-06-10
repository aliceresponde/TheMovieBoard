package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName

data class SerieResult(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("poster_path")
    val imageUrl: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    val popularity: Float
)
