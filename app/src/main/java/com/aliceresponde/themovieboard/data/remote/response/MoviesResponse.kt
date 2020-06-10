package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val movies : List<MovieResult>
)